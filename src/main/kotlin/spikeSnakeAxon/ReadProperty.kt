package spikeSnakeAxon

import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ReadProperty {

    @Autowired
    lateinit var propertyRepo: ReadPropertyRepository

    @QueryHandler
    fun ask(q: ReadPropertyQuery): List<ReadPropertyModel> = propertyRepo.get()

    @EventHandler
    fun project(e: PropertyCreatedEvent) = propertyRepo.add(ReadPropertyModel(e.propertyId))

    @EventHandler
    fun project(e: PropertyValuated) = propertyRepo.update(e.propertyId, e.valuation)
}
