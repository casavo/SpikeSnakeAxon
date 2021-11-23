package spikeSnakeAxon.commands

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.*

data class EvaluatePropertyCommand (
    @TargetAggregateIdentifier
    val propertyId: UUID,
)