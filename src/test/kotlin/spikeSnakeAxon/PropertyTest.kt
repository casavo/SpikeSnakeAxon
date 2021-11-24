package spikeSnakeAxon

import org.axonframework.test.aggregate.AggregateTestFixture
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito
import org.mockito.internal.matchers.Any
import spikeSnakeAxon.commands.CreatePropertyCommand
import spikeSnakeAxon.commands.EvaluatePropertyCommand
import spikeSnakeAxon.domain.Property
import spikeSnakeAxon.domain.service.EvaluationService
import spikeSnakeAxon.events.PropertyCreated
import spikeSnakeAxon.events.PropertyValuated
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
            .expectEvents(PropertyCreated(propertyId, "zipCode",propertyData))
    }

    @Test
    fun `evaluate property`() {
        val propertyId = UUID.randomUUID()

        // FIXME: fix this test.
        fixture.given(PropertyCreated(propertyId, "zipCode",propertyData))
            .`when`(EvaluatePropertyCommand(propertyId))
            .expectEvents(PropertyValuated(propertyId, valuation))
    }
}