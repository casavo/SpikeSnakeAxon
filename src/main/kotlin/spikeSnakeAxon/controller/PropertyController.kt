package spikeSnakeAxon.controller

import org.axonframework.commandhandling.gateway.CommandGateway
import spikeSnakeAxon.commands.CreatePropertyCommand
import java.util.*
import javax.ws.rs.*

@Path("/v1/property")
class PropertyController(myCommandGateway: CommandGateway) {

    private var commandGateway: CommandGateway = myCommandGateway

    @POST
    fun create(@QueryParam("id") id: String) {
        val createThisPropertyCommand = CreatePropertyCommand(UUID.fromString(id), "12345", mapOf())
        commandGateway.send<CreatePropertyCommand>(createThisPropertyCommand)
    }

}