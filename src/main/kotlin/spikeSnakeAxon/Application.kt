package spikeSnakeAxon

import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*


@SpringBootApplication
class Application

fun main(args: Array<String>) {
    val appContext = SpringApplication.run(Application::class.java, *args)
}

// TODO: move this controller: https://stackoverflow.com/questions/31318107/spring-boot-cannot-access-rest-controller-on-localhost-404
@RestController
class PropertyController() {

    @Autowired
    private lateinit var commandGateway: CommandGateway

    @Autowired
    private lateinit var queryGateway: QueryGateway

    @PostMapping("/v1/property/{id}")
    fun create(@PathVariable id: String) {
        val createThisPropertyCommand = CreatePropertyCommand(UUID.fromString(id), "12345", mapOf())
        commandGateway.send<CreatePropertyCommand>(createThisPropertyCommand)
    }

    @GetMapping("/v1/property")
    fun get() = queryGateway.query(ReadPropertyQuery(), ResponseTypes.multipleInstancesOf(ReadPropertyModel::class.java))

}