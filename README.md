# Spike Snake axon

This repo is used to familiarize with the [Axon Framework](https://axoniq.io/)

### How it is structured
- single springboot application
- the `write` package contains the command APIs and the event sourcing core.
- the `read` package contains the read APIs and use a different read-model

Both package could be moved in a different and separated microservices.

### How to run it
- start the axon server with `docker-compose up`
- execute the string application with `./gradlew bootRun`
- trigger the related endpoint
- explore the result [accessing the axon server UI](http//:localhost:8024)

###  Considerations:
#### [TSELLERS-493 Spike](https://casavo.atlassian.net/browse/TSELLERS-493)
- Complexity is high. Maybe with `SpringBoot` we better focus on real problems? (maybe not, we just have more magic and things running)
- `CQRS` means lots of infrastructure (command but, event bus, event store ...)
- attend an official Axon course.
- `CQRS` and event sourcing look very interesting.
- The way how `AXON` handles the event sourcing related stuff is very intuitive and maintainable.
- Currently, we are not ready to adopt it in `production` but we think that the things that we have learnt
in this Spike will be useful for the tribe's ecosystem. We should try to keep the already existing svcs
as small as possible in order to switch to it when our knowledge about the framework will be enough.
--- 

### Further investigation
- Try to use it with `Quarkus` (we had some problems with DI - parameterResolverError)
- Try to use it without `axon server` and implement a db based `event/token` store. A draft branch can be found [here](https://github.com/casavo/SpikeSnakeAxon/tree/database)
- Welcome production things (monitoring, scaling, deployment, disaster recovery, etc..)
- Do we need axon enterprise? (we are in containers, not VM)

--- 
### Useful resources
- [Existing kotlin project](https://github.com/srbarrios/spring-axon-kotlin-example)
- [How to write tests - concepts](https://medium.com/digitalfrontiers/test-first-development-using-axon-framework-72f6d2a3d6f2)
- [How to write tests - Implementation](https://www.novatec-gmbh.de/en/blog/testing-event-sourcing-applications/)
- [Aggregates commands and events example](https://www.novatec-gmbh.de/en/blog/event-sourcing-spring-boot-axon)
- [Quarkus with Axon example](https://github.com/frezelth/axon-quarkus)
