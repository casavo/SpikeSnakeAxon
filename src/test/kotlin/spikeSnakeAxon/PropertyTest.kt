package spikeSnakeAxon

import org.axonframework.test.aggregate.AggregateTestFixture
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
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
}