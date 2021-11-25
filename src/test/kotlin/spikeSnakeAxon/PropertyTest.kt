package spikeSnakeAxon

import org.axonframework.test.aggregate.AggregateTestFixture
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import spikeSnakeAxon.CreatePropertyCommand
import spikeSnakeAxon.EvaluatePropertyCommand
import spikeSnakeAxon.Property
import spikeSnakeAxon.domain.EvaluationService
import spikeSnakeAxon.PropertyCreatedEvent
import spikeSnakeAxon.PropertyValuated
import java.util.*

class PropertyTest {
    private lateinit var fixture: AggregateTestFixture<Property>
    private val mockedEvaluationService =  Mockito.mock(EvaluationService::class.java)
    private val valuation = 78.0
    private val propertyData = mapOf("City" to "milan")

    @BeforeEach
    fun setUp() {
        fixture = AggregateTestFixture(Property::class.java)
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