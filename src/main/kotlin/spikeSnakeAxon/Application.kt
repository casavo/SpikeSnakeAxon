package spikeSnakeAxon

import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import spikeSnakeAxon.CreatePropertyCommand
import java.util.*


@SpringBootApplication
class Application

fun main(args: Array<String>) {
    val appContext = SpringApplication.run(Application::class.java, *args)
}

// TODO: move this controller: https://stackoverflow.com/questions/31318107/spring-boot-cannot-access-rest-controller-on-localhost-404
@RestController
class PropertyController(myCommandGateway: CommandGateway) {

    @Autowired
    private var commandGateway: CommandGateway = myCommandGateway

    @PostMapping("/v1/property")
    fun create() {
        val createThisPropertyCommand = CreatePropertyCommand(UUID.randomUUID(), "12345", mapOf())
        commandGateway.send<CreatePropertyCommand>(createThisPropertyCommand)
    }

    @GetMapping()
    fun get() = "ciao"

}