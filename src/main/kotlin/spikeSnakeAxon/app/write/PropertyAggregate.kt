package spikeSnakeAxon.app.write

import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle.apply
import org.axonframework.spring.stereotype.Aggregate
import spikeSnakeAxon.app.events.PropertyCreatedEvent
import spikeSnakeAxon.app.events.PropertyValuated
import spikeSnakeAxon.logger
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
        logger.info("PropertyAggregate - CommandHandler - Handle CreatePropertyCommand")
        val aggregateId = command.propertyId
        apply(PropertyCreatedEvent(aggregateId, command.zipCode, command.data))
    }

    @CommandHandler
    fun handle(command: EvaluatePropertyCommand, evaluationService: EvaluationService){
        logger.info("PropertyAggregate - CommandHandler - Handle EvaluatePropertyCommand")
        val valuation = evaluationService.valuateProperty(propertyData)
        apply(PropertyValuated(command.propertyId, valuation))
    }

    @EventSourcingHandler
    fun on(event: PropertyCreatedEvent) {
        logger.info("PropertyAggregate - EventSourcing - Handle PropertyCreatedEvent")
        propertyId = event.propertyId
        zipCode = event.zipCode
        propertyData = event.data
    }

    @EventSourcingHandler
    fun on(event: PropertyValuated) {
        logger.info("PropertyAggregate - EventSourcing - Handle PropertyValuated")
        valuation = event.valuation
    }

}
