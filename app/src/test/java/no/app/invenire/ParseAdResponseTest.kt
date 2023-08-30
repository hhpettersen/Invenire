package no.app.invenire

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.EnumJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import models.AdResponse
import models.AdType
import org.junit.Before
import org.junit.Test

class ParseAdResponseTest {

    private lateinit var moshi: Moshi
    private lateinit var adapter: JsonAdapter<AdResponse>

    @Before
    fun setup() {
        moshi = Moshi.Builder()
            .add(
                AdType::class.java,
                EnumJsonAdapter.create(
                    AdType::class.java
                ).withUnknownFallback(
                    AdType.UNKNOWN
                )
            )
            .addLast(KotlinJsonAdapterFactory())
            .build()

        adapter = moshi.adapter(AdResponse::class.java)
    }

    @Test
    fun `validate parsing of ad-response`() {

        val response = adapter.fromJson(readResourceFile("sample_response.json"))!!

        assertNotNull(response)
        assertEquals(99, response.items.size)

        val firstAdItem = response.items[0]

        assertEquals(AdType.BAP, firstAdItem.adType)
        assertEquals("Engerdal", firstAdItem.location)
        assertEquals(10000, firstAdItem.price?.value)
    }

    @Test
    fun `validate successful parsing of ad-response with missing data`() {
        val response = adapter.fromJson(readResourceFile("sample_response_missing_data.json"))!!

        assertNotNull(response)
        assertEquals(1, response.items.size)

        val firstAdItem = response.items[0]

        assertEquals(AdType.UNKNOWN, firstAdItem.adType)
        assertNull(firstAdItem.location)
        assertNull(firstAdItem.price)
    }
}
