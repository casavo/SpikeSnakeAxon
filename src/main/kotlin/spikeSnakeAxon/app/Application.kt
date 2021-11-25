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
import spikeSnakeAxon.app.read.ReadPropertyModel
import spikeSnakeAxon.app.read.ReadPropertyQuery
import spikeSnakeAxon.app.write.CreatePropertyCommand
import java.util.*


@SpringBootApplication
class Application

fun main(args: Array<String>) {
    val appContext = SpringApplication.run(Application::class.java, *args)
}

// TODO: move this controller: https://stackoverflow.com/questions/31318107/spring-boot-cannot-access-rest-controller-on-localhost-404
