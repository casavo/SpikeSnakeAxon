package spikeSnakeAxon.domain

import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle.apply
import org.axonframework.modelling.command.AggregateRoot
import spikeSnakeAxon.commands.CreatePropertyCommand
import spikeSnakeAxon.commands.EvaluatePropertyCommand
import spikeSnakeAxon.domain.service.EvaService
import spikeSnakeAxon.events.PropertyCreated
import spikeSnakeAxon.events.PropertyValuated
import java.util.*
import kotlin.properties.Delegates

@AggregateRoot
class Property {

    @AggregateIdentifier
    private lateinit var propertyId: UUID
    private lateinit var zipCode: String
    private lateinit var propertyData: Any
    private var valuation by Delegates.notNull<Double>()

    lateinit var eva: EvaService

    constructor()

    @CommandHandler
    constructor(command: CreatePropertyCommand) {
        val aggregateId = command.propertyId
        apply(PropertyCreated(aggregateId, command.zipCode))
    }

    @CommandHandler
    constructor(command: EvaluatePropertyCommand, injectedEva: EvaService){
        eva = injectedEva
        val valuation = eva.valuateProperty(propertyData)
        apply(PropertyValuated(command.propertyId, valuation))
    }

    @EventSourcingHandler
    fun on(event: PropertyCreated) {
        propertyId = event.propertyId //todo: this should be done by the framewrok, isn't it?
        zipCode = event.zipCode
    }

    @EventSourcingHandler
    fun on(event: PropertyValuated) {
        valuation = event.valuation
    }

}
