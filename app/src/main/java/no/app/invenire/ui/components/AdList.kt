package no.app.invenire.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import no.app.invenire.ui.models.network.AdType
import no.app.invenire.ui.models.ui.AdItemUI
import no.app.invenire.ui.models.ui.Ads
import no.app.invenire.ui.theme.InvenireTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AdList(
    modifier: Modifier = Modifier,
    ads: Ads,
    onItemSelected: (String) -> Unit,
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier,
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
}

@Preview
@Composable
fun PreviewAdList() {
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
            onItemSelected = {},
        )
    }
}
