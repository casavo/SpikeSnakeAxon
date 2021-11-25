package spikeSnakeAxon.events

import java.util.*

data class PropertyCreatedEvent(
    val propertyId: UUID,
    val zipCode: String,
    val data: Map<String, String>
)