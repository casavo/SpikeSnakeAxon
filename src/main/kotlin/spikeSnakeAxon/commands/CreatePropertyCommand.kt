package spikeSnakeAxon.commands

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.*

data class CreatePropertyCommand(

    @TargetAggregateIdentifier
    val propertyId: UUID,
    val zipCode: String,
    val data: Map<String, String>
)