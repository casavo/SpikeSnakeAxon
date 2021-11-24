package spikeSnakeAxon.domain.service

import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class EvaService {

    fun valuateProperty(propertyData: Any) = Math.random() * 100
}