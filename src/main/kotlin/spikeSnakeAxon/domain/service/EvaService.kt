package spikeSnakeAxon.domain.service

interface EvaluationService{
    fun valuateProperty(propertyData: Any): Double
}

class EvaService: EvaluationService {
    override fun valuateProperty(propertyData: Any) = Math.random() * 100
}