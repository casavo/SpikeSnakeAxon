package spikeSnakeAxon.app.write

import org.springframework.stereotype.Service

interface EvaluationService{
    abstract fun valuateProperty(propertyData: Any): Double
}

@Service
class EvaService: EvaluationService {

    override fun valuateProperty(propertyData: Any) = Math.random() * 100
}