package spikeSnakeAxon

import org.springframework.stereotype.Repository
import java.util.*

data class ReadPropertyModel(val id: UUID, var valuation: Double? = null)

@Repository
class ReadPropertyRepository {

    val props = mutableMapOf<UUID, ReadPropertyModel>()

    fun add(property: ReadPropertyModel) = props.putIfAbsent(property.id, property)

    fun get(id: UUID) = props.getOrDefault(id, null)

    fun get(): List<ReadPropertyModel> = props.toList().map { it.second }

    fun update(id: UUID, valuation: Double) {
        props.getOrDefault(id, null)?.let { it.valuation = valuation }
    }
}