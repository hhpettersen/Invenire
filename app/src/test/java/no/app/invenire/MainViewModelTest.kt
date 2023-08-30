package no.app.invenire

import app.cash.turbine.test
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import no.app.invenire.repository.TestAdRepositoryImpl
import no.app.invenire.ui.models.network.AdType
import no.app.invenire.ui.models.ui.AdItemUI
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainViewModelTest {

    private lateinit var viewModel: MainViewModel
    private val adRepository = TestAdRepositoryImpl(
        ads = listOf(
            AdItemUI(
                id = "1",
                adType = AdType.BAP,
                description = "description",
                price = "1 000",
                location = "Molde",
                imageUrl = "https://images.finncdn.no/dynamic/480x360c/1.jpg",
                isFavorite = false,
            ),
            AdItemUI(
                id = "2",
                adType = AdType.B2B,
                description = "description",
                price = "2 000",
                location = "Skjolde",
                imageUrl = "https://images.finncdn.no/dynamic/480x360c/1.jpg",
                isFavorite = true,
            ),
        )
    )
    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = MainViewModel(adRepository)
    }

    @Test
    fun `refreshAds should clear ads and set refreshing to true`() = runTest {
        viewModel.state.test {
            assertEquals(2, awaitItem().allAds.size)

            viewModel.refreshAds()

            awaitItem().let { event ->
                assertEquals(true, event.refreshing)
                assertTrue(event.allAds.isEmpty())
            }

            awaitItem().let { event ->
                assertEquals(false, event.refreshing)
                assertEquals(2, event.allAds.size)
            }
        }
    }

    @Test
    fun `add and remote ad from favorites`() = runTest {
        viewModel.state.test {
            assertEquals(2, awaitItem().allAds.size)

            viewModel.onItemSelected("1")

            awaitItem().let { event ->
                assertEquals(true, event.allAds.first().isFavorite)
                assertEquals(adRepository.cachedAds.first().id, event.allAds.first().id)
            }

            viewModel.onItemSelected("1")

            awaitItem().let { event ->
                assertEquals(false, event.allAds.first().isFavorite)
                assertTrue(adRepository.cachedAds.isEmpty())
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cancel()
    }

    // More tests...
}
