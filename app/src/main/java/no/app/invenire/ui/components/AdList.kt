package no.app.invenire.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import no.app.invenire.ui.models.network.AdType
import no.app.invenire.ui.models.ui.AdItemUI
import no.app.invenire.ui.models.ui.Ads
import no.app.invenire.ui.theme.InvenireTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AdList(
    modifier: Modifier = Modifier,
    ads: Ads,
    refreshing: Boolean,
    onRefreshed: () -> Unit,
    onItemSelected: (String) -> Unit,
) {
    val pullRefreshState = rememberPullRefreshState(refreshing, { onRefreshed() })

    Box(modifier = modifier.fillMaxSize()) {
        LazyVerticalStaggeredGrid(
            modifier = modifier.pullRefresh(pullRefreshState),
            columns = StaggeredGridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalItemSpacing = 32.dp,
        ) {
            items(ads.size) { index ->
                AdCard(
                    modifier = Modifier,
                    ad = ads[index],
                    onItemSelected = onItemSelected,
                )
            }
        }
        PullRefreshIndicator(
            modifier = Modifier.align(Alignment.TopCenter),
            refreshing = refreshing,
            state = pullRefreshState,
        )
    }
}

@Preview
@Composable
private fun PreviewAdList() {
    InvenireTheme {
        AdList(
            ads = listOf(
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
                    id = "1",
                    adType = AdType.BAP,
                    price = "1000",
                    location = "Oslo",
                    imageUrl = "https://images.finncdn.no/dynamic/480x360c/2023/3/vertical-0/21/3/295/695/523_1539723838.jpg",
                    isFavorite = false,
                ),
                AdItemUI(
                    description = "Some long description about the ad or something like that",
                    id = "1",
                    adType = AdType.BAP,
                    price = "1000",
                    location = "Oslo",
                    imageUrl = "https://images.finncdn.no/dynamic/480x360c/2023/3/vertical-0/21/3/295/695/523_1539723838.jpg",
                    isFavorite = true,
                ),
            ),
            refreshing = false,
            onItemSelected = {},
            onRefreshed = {},
        )
    }
}
