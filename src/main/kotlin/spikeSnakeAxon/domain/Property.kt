package spikeSnakeAxon.domain

import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle.apply
import org.axonframework.modelling.command.AggregateRoot
import spikeSnakeAxon.commands.CreatePropertyCommand
import spikeSnakeAxon.commands.EvaluatePropertyCommand
import spikeSnakeAxon.domain.service.EvaValuationService
import spikeSnakeAxon.events.PropertyCreated
import java.util.*

@AggregateRoot
class Property {

    @AggregateIdentifier
    private lateinit var propertyId: UUID
    private lateinit var zipCode: String
    private lateinit var eva: EvaValuationService // TODO: question: is it correct to use an external service in an aggregate?

    constructor()

    @CommandHandler
    constructor(command: CreatePropertyCommand) {
        val aggregateId = command.propertyId
        apply(PropertyCreated(aggregateId, command.zipCode))
    }

    @CommandHandler
    constructor(command: EvaluatePropertyCommand){
        // TODO: evaluate property using eva
        // TODO: emit related event.
    }

    @EventSourcingHandler
    fun on(event: PropertyCreated) {
        propertyId = event.propertyId
        zipCode = event.zipCode
    }

}
