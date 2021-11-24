package spikeSnakeAxon.controller

import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import spikeSnakeAxon.commands.CreatePropertyCommand
import java.util.*

@RequestMapping("/v1/property")
@RestController
class PropertyController(myCommandGateway: CommandGateway) {

    private var commandGateway: CommandGateway = myCommandGateway

    @PostMapping(path = ["{id}"])
    fun create(@PathVariable("id") id: String) {
        val createThisPropertyCommand = CreatePropertyCommand(UUID.fromString(id), "12345", mapOf())
        commandGateway.send<CreatePropertyCommand>(createThisPropertyCommand)
    }

}