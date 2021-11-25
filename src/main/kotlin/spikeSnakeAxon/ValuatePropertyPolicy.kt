package spikeSnakeAxon

import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.eventhandling.EventHandler
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class ValuatePropertyPolicy {

    final val logger: Logger = LoggerFactory.getLogger(PropertyAggregate::class.java)

    @EventHandler
    fun policy(event: PropertyCreatedEvent, commandGateway: CommandGateway) {
        logger.info("EventHandler - PropertyCreatedEvent")
        val command = EvaluatePropertyCommand(event.propertyId)
//        commandGateway.send<EvaluatePropertyCommand>(command)
    }
}
