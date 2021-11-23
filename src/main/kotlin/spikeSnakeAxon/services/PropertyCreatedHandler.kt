package spikeSnakeAxon.services

import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.eventhandling.EventHandler
import spikeSnakeAxon.commands.EvaluatePropertyCommand
import spikeSnakeAxon.events.PropertyCreated


class PropertyCreatedHandler {
    // TODO: init this attribute
    private lateinit var commandGateway: CommandGateway

    @EventHandler
    fun on(event: PropertyCreated) {
        commandGateway.send<EvaluatePropertyCommand>(EvaluatePropertyCommand(event.propertyId))
    }
}