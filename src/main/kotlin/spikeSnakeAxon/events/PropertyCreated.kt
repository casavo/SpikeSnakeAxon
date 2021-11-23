package spikeSnakeAxon.events

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.*

data class PropertyCreated(
    val propertyId: UUID,
    val zipCode: String
)