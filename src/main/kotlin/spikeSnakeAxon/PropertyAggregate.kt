package spikeSnakeAxon

import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle.apply
import org.axonframework.spring.stereotype.Aggregate
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*

@Aggregate
class PropertyAggregate {

    @AggregateIdentifier
    private lateinit var propertyId: UUID
    private lateinit var zipCode: String
    private lateinit var propertyData: Any
    private var valuation: Double? = null

    final val logger: Logger = LoggerFactory.getLogger(PropertyAggregate::class.java)

    constructor()

    @CommandHandler
    constructor(command: CreatePropertyCommand) {
        logger.info("CommandHandler - CreatePropertyCommand ")
        val aggregateId = command.propertyId
        apply(PropertyCreatedEvent(aggregateId, command.zipCode, command.data))
    }

    @CommandHandler
    fun handle(command: EvaluatePropertyCommand, evaluationService: EvaluationService){
        logger.info("CommandHandler - EvaluatePropertyCommand ")
        val valuation = evaluationService.valuateProperty(propertyData)
        apply(PropertyValuated(command.propertyId, valuation))
    }

    @EventSourcingHandler
    fun on(event: PropertyCreatedEvent) {
        logger.info("EventSourcingHandler - PropertyCreatedEvent ")
        propertyId = event.propertyId
        zipCode = event.zipCode
        propertyData = event.data
    }

    @EventSourcingHandler
    fun on(event: PropertyValuated) {
        logger.info("EventSourcingHandler - PropertyValuated ")
        valuation = event.valuation
    }

}
