package spikeSnakeAxon

import org.axonframework.commandhandling.CommandHandler
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.eventhandling.EventHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle.apply
import org.axonframework.spring.stereotype.Aggregate
import spikeSnakeAxon.CreatePropertyCommand
import spikeSnakeAxon.EvaluatePropertyCommand
import spikeSnakeAxon.EvaluationService
import spikeSnakeAxon.PropertyCreatedEvent
import spikeSnakeAxon.PropertyValuated
import java.util.*

@Aggregate
class PropertyAggregate {

    @AggregateIdentifier
    private lateinit var propertyId: UUID
    private lateinit var zipCode: String
    private lateinit var propertyData: Any
    private var valuation: Double? = null


    constructor()

    @CommandHandler
    constructor(command: CreatePropertyCommand) {
        val aggregateId = command.propertyId
        apply(PropertyCreatedEvent(aggregateId, command.zipCode, command.data))
    }

    @CommandHandler
    fun handle(command: EvaluatePropertyCommand, evaluationService: EvaluationService){
        val valuation = evaluationService.valuateProperty(propertyData)
        apply(PropertyValuated(command.propertyId, valuation))
    }

    @EventSourcingHandler
    fun on(event: PropertyCreatedEvent) {
        propertyId = event.propertyId
        zipCode = event.zipCode
        propertyData = event.data
    }

    @EventSourcingHandler
    fun on(event: PropertyValuated) {
        valuation = event.valuation
    }

}
