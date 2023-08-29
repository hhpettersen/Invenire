package no.app.invenire.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import no.app.invenire.ViewState
import no.app.invenire.ui.components.AdFilter
import no.app.invenire.ui.components.AdList
import no.app.invenire.ui.components.RowOfAdFilters
import no.app.invenire.ui.models.network.AdType
import no.app.invenire.ui.models.ui.AdItemUI
import no.app.invenire.ui.theme.InvenireTheme

@Composable
fun AdsScreen(
    state: ViewState,
    onFilterSelected: (AdFilter) -> Unit,
    onRefreshed: () -> Unit,
    onItemSelected: (String) -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            RowOfAdFilters(
                selectedFilter = state.selectedFilter,
                onFilterSelected = onFilterSelected
            )
            AdList(
                ads = state.filteredAds,
                refreshing = state.refreshing,
                onRefreshed = onRefreshed,
                onItemSelected = onItemSelected
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAdsScreen() {
    val ads = listOf(
        AdItemUI(
            description = "Some long description about the ad or something like that",
            id = "1",
            adType = AdType.BAP,
            price = "1000",
            location = "Oslo",
            imageUrl = "https://images.finncdn.no/dynamic/480x360c/2023/3/vertical-0/21/3/295/695/523_1539723838.jpg",
            isFavorite = false,
        ),
        AdItemUI(
            description = "Some long description about the ad or something like that",
            id = "2",
            adType = AdType.BAP,
            price = "1000",
            location = "Oslo",
            imageUrl = "https://images.finncdn.no/dynamic/480x360c/2023/3/vertical-0/21/3/295/695/523_1539723838.jpg",
            isFavorite = false,
        ),
    )

    var adsState by remember { mutableStateOf(ads) }
    var selectedFilter by remember { mutableStateOf<AdFilter?>(null) }

    InvenireTheme {
        AdsScreen(
            state = ViewState(
                refreshing = false,
                selectedFilter = selectedFilter,
                allAds = adsState,
            ),
            onFilterSelected = { filter ->
                selectedFilter = if (selectedFilter == filter) null else filter
            },
            onRefreshed = {},
            onItemSelected = { itemId ->
                adsState = adsState.map {
                    if (it.id == itemId) it.copy(isFavorite = !it.isFavorite) else it
                }
            },
        )
    }
}
