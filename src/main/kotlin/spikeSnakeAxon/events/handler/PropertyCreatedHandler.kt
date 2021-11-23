package spikeSnakeAxon.events.handler

import org.axonframework.eventhandling.EventHandler
import spikeSnakeAxon.events.PropertyCreated

@EventHandler
fun on(event: PropertyCreated) {
    // TODO: dispatch command
}
