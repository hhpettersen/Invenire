package no.app.invenire.util

import java.util.Locale

/**
 * * Using [Locale.US] ensures consistent comma-based thousand separators, ensuring predictable replacement with spaces.
 */
object NumberFormatter {
    fun formatWithSpaces(number: Int): String =
        String.format(Locale.US, "%,d", number).replace(',', ' ')
}
