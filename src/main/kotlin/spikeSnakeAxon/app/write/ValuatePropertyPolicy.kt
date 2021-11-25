package spikeSnakeAxon.app.write

import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Service
import spikeSnakeAxon.app.write.EvaluatePropertyCommand
import spikeSnakeAxon.app.write.PropertyCreatedEvent

@Service
class ValuatePropertyPolicy {

    @EventHandler
    fun policy(event: PropertyCreatedEvent, commandGateway: CommandGateway) {
        val command = EvaluatePropertyCommand(event.propertyId)
        commandGateway.send<EvaluatePropertyCommand>(command)
    }
}
