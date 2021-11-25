package spikeSnakeAxon.app.write

import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import spikeSnakeAxon.app.read.ReadPropertyModel
import spikeSnakeAxon.app.read.ReadPropertyQuery
import java.util.*

@RestController
class WritePropertyController() {

    @Autowired
    private lateinit var commandGateway: CommandGateway

    @PostMapping("/v1/property/{id}")
    fun create(@PathVariable id: String) {
        val createThisPropertyCommand = CreatePropertyCommand(UUID.fromString(id), "12345", mapOf())
        commandGateway.send<CreatePropertyCommand>(createThisPropertyCommand)
    }

}