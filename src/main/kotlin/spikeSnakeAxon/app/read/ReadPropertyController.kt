package spikeSnakeAxon.app.read

import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class ReadPropertyController() {

    @Autowired
    private lateinit var queryGateway: QueryGateway

    @GetMapping("/v1/property")
    fun get() = queryGateway.query(ReadPropertyQuery(), ResponseTypes.multipleInstancesOf(ReadPropertyModel::class.java))
}