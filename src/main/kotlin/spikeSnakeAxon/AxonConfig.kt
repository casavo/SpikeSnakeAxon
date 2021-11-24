package spikeSnakeAxon

import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.config.Configuration
import org.axonframework.config.DefaultConfigurer
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Produces

/* https://docs.axoniq.io/reference-guide/v/3.1/part-i-getting-started/quick-start#configuration */
@ApplicationScoped
class AxonConfiguration() {

    lateinit var commandGateway: CommandGateway

    private var conf: Configuration

    init {
        val eventstore = EmbeddedEventStore.builder().storageEngine(InMemoryEventStorageEngine()).build()
        conf = DefaultConfigurer.defaultConfiguration()
            .configureEventStore { eventstore }
//            .configureAggregate(Property.javaClass)
            .buildConfiguration()
        conf.start()
    }

    @Produces
    @ApplicationScoped
    fun commandGateway(): CommandGateway {
        return conf.commandGateway()
    }
}