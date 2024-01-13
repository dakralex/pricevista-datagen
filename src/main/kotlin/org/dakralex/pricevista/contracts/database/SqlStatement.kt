package org.dakralex.pricevista.contracts.database

fun interface SqlStatement {
    companion object {
        fun formatAttributes(sequence: Sequence<String>): String {
            return sequence.joinToString()
        }

        fun formatPlaceholders(sequence: Sequence<String>): String {
            return sequence.map(::formatPlaceholder).joinToString { ":$it" }
        }

        private fun formatPlaceholder(placeholder: String): String {
            return placeholder.splitToSequence("_")
                .map { it.replaceFirstChar(Char::uppercase) }
                .joinToString(separator = "")
        }
    }

    override fun toString(): String
}