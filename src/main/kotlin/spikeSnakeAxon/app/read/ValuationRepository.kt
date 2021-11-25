package spikeSnakeAxon.app.read

import org.springframework.stereotype.Repository
import java.util.*

data class Valuation(val id: UUID, var valuation: Double? = null)

@Repository
class ValuationRepository {

    val props = mutableMapOf<UUID, Valuation>()

    fun add(valuation: Valuation) = props.putIfAbsent(valuation.id, valuation)

    fun get(id: UUID) = props.getOrDefault(id, null)

    fun get(): List<Valuation> = props.toList().map { it.second }

    fun update(id: UUID, valuation: Double) {
        props.getOrDefault(id, null)?.let { it.valuation = valuation }
    }
}