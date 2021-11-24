package spikeSnakeAxon

import org.axonframework.test.aggregate.AggregateTestFixture
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import spikeSnakeAxon.commands.CreatePropertyCommand
import spikeSnakeAxon.commands.EvaluatePropertyCommand
import spikeSnakeAxon.domain.Property
import spikeSnakeAxon.events.PropertyCreated
import spikeSnakeAxon.events.PropertyValuated
import java.util.*

class PropertyTest {
    private lateinit var fixture: AggregateTestFixture<Property>

    @BeforeEach
    fun setUp() {
        fixture = AggregateTestFixture(Property::class.java)
    }

    @Test
    fun `create property`() {
        val propertyId = UUID.randomUUID()
        fixture.givenNoPriorActivity()
            .`when`(CreatePropertyCommand(propertyId, "zipCode"))
            .expectEvents(PropertyCreated(propertyId, "zipCode"))
    }

    @Test
    fun `evaluate property`() {
        val propertyId = UUID.randomUUID()

        fixture.given(PropertyCreated(propertyId, "stringZipCode"))
            .`when`(EvaluatePropertyCommand(propertyId))
            .expectSuccessfulHandlerExecution()
            .expectEvents(PropertyValuated(propertyId, 2222.0))
    }
}