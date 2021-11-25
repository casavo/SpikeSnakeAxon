package spikeSnakeAxon.app.events

import java.util.*

data class PropertyValuated(
    val propertyId: UUID,
    val valuation: Double
)