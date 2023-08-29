package no.app.invenire.util

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotSame
import org.junit.Test

class NumberFormatterTest {

    @Test
    fun `test formatWithSpaces`() {
        assertEquals("123 456 789", NumberFormatter.formatWithSpaces(123456789))
        assertNotSame("123456789", NumberFormatter.formatWithSpaces(123456789))
        assertEquals("1", NumberFormatter.formatWithSpaces(1))
        assertEquals("-1 000", NumberFormatter.formatWithSpaces(-1000))
    }
}
