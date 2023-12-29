package org.dakralex.pricevista.parser

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object IntAsStringSerializer : KSerializer<Int> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(
            "kotlinx.serialization.DoubleAsStringSerializer",
            PrimitiveKind.STRING
        )

    override fun serialize(encoder: Encoder, value: Int) {
        encoder.encodeString(value.toString())
    }

    override fun deserialize(decoder: Decoder): Int {
        return decoder.decodeString().toInt()
    }
}