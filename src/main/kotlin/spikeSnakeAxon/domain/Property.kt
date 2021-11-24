package spikeSnakeAxon.domain

import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle.apply
import org.axonframework.modelling.command.AggregateMember
import org.axonframework.modelling.command.AggregateRoot
import spikeSnakeAxon.commands.CreatePropertyCommand
import spikeSnakeAxon.commands.EvaluatePropertyCommand
import spikeSnakeAxon.domain.service.EvaluationService
import spikeSnakeAxon.events.PropertyCreated
import spikeSnakeAxon.events.PropertyValuated
import java.util.*

@AggregateRoot
class Property {

    @AggregateIdentifier
    private lateinit var propertyId: UUID
    private lateinit var zipCode: String
    private lateinit var propertyData: Any
    private var valuation: Double? = null


    constructor()

    @CommandHandler
    constructor(command: CreatePropertyCommand) {
        val aggregateId = command.propertyId
        apply(PropertyCreated(aggregateId, command.zipCode, command.data))
    }

    @CommandHandler
    fun handle(command: EvaluatePropertyCommand, evaluationService: EvaluationService){
        val valuation = evaluationService.valuateProperty(propertyData)
        apply(PropertyValuated(command.propertyId, valuation))
    }

    @EventSourcingHandler
    fun on(event: PropertyCreated) {
        propertyId = event.propertyId
        zipCode = event.zipCode
        propertyData = event.data
    }

    @EventSourcingHandler
    fun on(event: PropertyValuated) {
        valuation = event.valuation
    }

}
