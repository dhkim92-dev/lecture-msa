package kr.dohoon_kim.msa.auth_service.domain

import java.io.Serializable
import java.util.*

data class ID<R, V: Serializable>(
    val reference: Class<R>,
    val value: V
): Serializable {

    companion object {
        fun <R, V: Serializable> of(reference: Class<R>, value: V) : ID<R, V> {
            requireNotNull(reference) { "reference must be provided." }
            requireNotNull(value) { "value must be provided." }
            return ID<R, V>(reference, value)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        other as ID<*, *>
        return reference == other.reference && value == other.value
    }

    override fun hashCode(): Int {
        return Objects.hash(reference, value)
    }

    override fun toString(): String {
        return "Id(reference=${reference.simpleName}, value=$value)"
    }
}