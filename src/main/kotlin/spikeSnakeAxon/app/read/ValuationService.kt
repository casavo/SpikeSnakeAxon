package spikeSnakeAxon.app.read

import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import spikeSnakeAxon.app.write.PropertyCreatedEvent
import spikeSnakeAxon.app.write.PropertyValuated

@Component
class ValuationService {

    @Autowired
    lateinit var propertyRepo: ValuationRepository

    @QueryHandler
    fun ask(q: ValuationQuery): List<Valuation> = propertyRepo.get()

    @EventHandler
    fun project(e: PropertyCreatedEvent) = propertyRepo.add(Valuation(e.propertyId))

    @EventHandler
    fun project(e: PropertyValuated) = propertyRepo.update(e.propertyId, e.valuation)
}
