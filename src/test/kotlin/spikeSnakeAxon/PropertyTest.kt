package spikeSnakeAxon

import io.quarkus.test.junit.QuarkusTest
import org.axonframework.test.aggregate.AggregateTestFixture
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import spikeSnakeAxon.commands.CreatePropertyCommand
import spikeSnakeAxon.domain.Property
import spikeSnakeAxon.events.PropertyCreated
import java.util.*

@QuarkusTest
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