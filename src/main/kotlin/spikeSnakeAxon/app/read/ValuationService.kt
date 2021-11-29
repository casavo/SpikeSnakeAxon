package spikeSnakeAxon.app.read

import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import spikeSnakeAxon.app.events.PropertyCreatedEvent
import spikeSnakeAxon.app.events.PropertyValuated
import spikeSnakeAxon.logger

@Component
class ValuationService {

    @Autowired
    lateinit var propertyRepo: ValuationRepository

    @QueryHandler
    fun ask(q: ValuationQuery): List<Valuation> {
        logger.info("ValuationService - QueryHandler - Handle ValuationQuery")
        return propertyRepo.get()
    }

    @EventHandler
    fun project(e: PropertyCreatedEvent) {
        logger.info("ValuationService - EventHandler - Handle PropertyCreatedEvent")
        propertyRepo.add(Valuation(e.propertyId))
    }

    @EventHandler
    fun project(e: PropertyValuated) {
        logger.info("ValuationService - EventHandler - Handle PropertyValuated")
        propertyRepo.update(e.propertyId, e.valuation)
    }
}
