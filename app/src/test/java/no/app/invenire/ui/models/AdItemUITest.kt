package no.app.invenire.ui.models

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import no.app.invenire.domain.AdImage
import no.app.invenire.domain.AdItem
import no.app.invenire.domain.AdType
import no.app.invenire.domain.Price
import org.junit.Test

class AdItemUITest {

    @Test
    fun `map AdItem with defined values to AdItemUI`() {
        val adItem = AdItem(
            id = "1",
            adType = AdType.BAP,
            description = "description",
            price = Price(1000),
            location = "Molde",
            image = AdImage("1.jpg"),
        )

        val adItemUI = adItem.toUiModel()

        assertEquals(adItem.id, adItemUI.id)
        assertTrue(adItemUI.adType == AdType.BAP)
        assertEquals(adItem.description, adItemUI.description)
        assertEquals("1 000", adItemUI.price)
        assertEquals(adItem.location, adItemUI.location)
        assertEquals("https://images.finncdn.no/dynamic/480x360c/1.jpg", adItemUI.imageUrl)
        assertFalse(adItemUI.isFavorite)
    }

    @Test
    fun `map AdItem with null values to AdItemUI`() {
        val adItem = AdItem(
            id = "1",
            adType = AdType.BAP,
            description = null,
            price = null,
            location = null,
            image = null,
        )

        val adItemUI = adItem.toUiModel()

        assertEquals(adItemUI.id, adItem.id)
        assertTrue(adItemUI.adType == AdType.BAP)
        assertNull(adItemUI.description)
        assertNull(adItemUI.price)
        assertNull(adItemUI.location)
        assertNull(adItemUI.imageUrl)
    }
}
