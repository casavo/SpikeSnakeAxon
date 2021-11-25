# Spike Snake axon

This repo is used to familiarize with the [Axon Framework](https://axoniq.io/)

## How it is structured
- single springboot application
- the `write` package contains the command APIs and the event sourcing core.
- the `read` package contains the read APIs and use a different read-model

Both package could be moved in a different and separated microservices.

## Considerations:
- Complexity is high. Maybe with springboot we better focus on real problems? (maybe not, we just have more magic and things running)
- CQRS means lots of infrastructure (command but, event bus, event store ...)
- we need axon enterprise (we are in containers, not VM)

## Useful resources
- [Existing kotlin project](https://github.com/srbarrios/spring-axon-kotlin-example)
- [How to write tests - concepts](https://medium.com/digitalfrontiers/test-first-development-using-axon-framework-72f6d2a3d6f2)
- [How to write tests - Implementation](https://www.novatec-gmbh.de/en/blog/testing-event-sourcing-applications/)
- [Aggregates commands and events example](https://www.novatec-gmbh.de/en/blog/event-sourcing-spring-boot-axon)
- [Quarkus with Axon example](https://github.com/frezelth/axon-quarkus)


## How to run it!
- start the axon server with `docker-compose up`
- execute the string application with `./gradlew bootRun`
- trigger the related endpoint
- explore the result [accessing the axon server UI](http//:localhost:8024) 