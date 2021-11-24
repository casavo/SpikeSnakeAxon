package spikeSnakeAxon

import org.axonframework.test.aggregate.AggregateTestFixture
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import spikeSnakeAxon.commands.CreatePropertyCommand
import spikeSnakeAxon.commands.EvaluatePropertyCommand
import spikeSnakeAxon.domain.Property
import spikeSnakeAxon.domain.service.EvaluationService
import spikeSnakeAxon.events.PropertyCreated
import spikeSnakeAxon.events.PropertyValuated
import java.util.*

class PropertyTest {
    private lateinit var fixture: AggregateTestFixture<Property>


    @BeforeEach
    fun setUp() {
        fixture = AggregateTestFixture(Property::class.java)
        fixture.registerInjectableResource(
            Mockito.mock(EvaluationService::class.java)
        )
    }

    @Test
    fun `create property`() {
        val propertyId = UUID.randomUUID()
        fixture.givenNoPriorActivity()
            .`when`(CreatePropertyCommand(propertyId, "zipCode", mapOf("City" to "milan")))
            .expectEvents(PropertyCreated(propertyId, "zipCode", mapOf("City" to "milan")))
    }

    @Test
    fun `evaluate property`() {
        val propertyId = UUID.randomUUID()

        fixture.given(PropertyCreated(propertyId, "zipCode", mapOf("City" to "milan")))
            .`when`(EvaluatePropertyCommand(propertyId))
            .expectSuccessfulHandlerExecution()
            .expectEvents(PropertyValuated(propertyId, 2222.0))
    }
}