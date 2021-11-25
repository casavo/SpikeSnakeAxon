package spikeSnakeAxon

import org.axonframework.test.aggregate.AggregateTestFixture
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import spikeSnakeAxon.events.PropertyCreatedEvent
import spikeSnakeAxon.events.PropertyValuated
import spikeSnakeAxon.app.write.*
import spikeSnakeAxon.write.CreatePropertyCommand
import spikeSnakeAxon.write.EvaluatePropertyCommand
import spikeSnakeAxon.write.EvaluationService
import spikeSnakeAxon.write.PropertyAggregate
import java.util.*

class PropertyTest {
    private lateinit var fixture: AggregateTestFixture<PropertyAggregate>
    private val mockedEvaluationService =  Mockito.mock(EvaluationService::class.java)
    private val valuation = 78.0
    private val propertyData = mapOf("City" to "milan")

    @BeforeEach
    fun setUp() {
        fixture = AggregateTestFixture(PropertyAggregate::class.java)
        fixture.registerInjectableResource(mockedEvaluationService)

        Mockito.`when`(
            mockedEvaluationService.valuateProperty(propertyData)
        ).thenReturn(valuation)
    }

    @Test
    fun `create property`() {
        val propertyId = UUID.randomUUID()

        fixture.givenNoPriorActivity()
            .`when`(CreatePropertyCommand(propertyId, "zipCode", propertyData))
            .expectEvents(PropertyCreatedEvent(propertyId, "zipCode",propertyData))
    }

    @Test
    fun `evaluate property`() {
        val propertyId = UUID.randomUUID()

        fixture.given(PropertyCreatedEvent(propertyId, "zipCode",propertyData))
            .`when`(EvaluatePropertyCommand(propertyId))
            .expectEvents(PropertyValuated(propertyId, valuation))
    }
}