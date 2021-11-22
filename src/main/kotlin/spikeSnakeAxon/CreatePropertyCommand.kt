package spikeSnakeAxon

import java.util.*

data class CreatePropertyCommand(
    val propertyId: UUID,
    val zipCode: String
)