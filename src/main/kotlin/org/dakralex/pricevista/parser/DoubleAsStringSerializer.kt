package org.dakralex.pricevista.parser

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object DoubleAsStringSerializer : KSerializer<Double> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(
            "kotlinx.serialization.DoubleAsStringSerializer",
            PrimitiveKind.STRING
        )

    override fun serialize(encoder: Encoder, value: Double) {
        encoder.encodeString(value.toString())
    }

    override fun deserialize(decoder: Decoder): Double {
        return decoder.decodeString().toDouble()
    }
}