package spikeSnakeAxon.app.write

import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Service
import spikeSnakeAxon.app.events.PropertyCreatedEvent
import spikeSnakeAxon.logger

@Service
class ValuatePropertyPolicy {

    @EventHandler
    fun policy(event: PropertyCreatedEvent, commandGateway: CommandGateway) {
        logger.info("ValuatePropertyPolicy - EventHandler - Handle PropertyCreatedEvent")
        val command = EvaluatePropertyCommand(event.propertyId)
        commandGateway.send<EvaluatePropertyCommand>(command)
    }
}
