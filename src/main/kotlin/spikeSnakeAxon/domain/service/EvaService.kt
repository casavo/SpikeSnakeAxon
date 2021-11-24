package spikeSnakeAxon.domain.service

import javax.enterprise.context.ApplicationScoped

interface EvaluationService{
    fun valuateProperty(propertyData: Any): Double
}

@ApplicationScoped
class EvaService: EvaluationService {
    override fun valuateProperty(propertyData: Any) = Math.random() * 100
}