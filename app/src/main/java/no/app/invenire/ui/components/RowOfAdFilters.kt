package no.app.invenire.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import no.app.invenire.R
import no.app.invenire.ui.models.network.AdType
import no.app.invenire.ui.theme.InvenireTheme
import no.app.invenire.ui.theme.Typography

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RowOfAdFilters(
    modifier: Modifier = Modifier,
    selectedFilter: AdFilter?,
    onFilterSelected: (AdFilter) -> Unit,
) {
    FlowRow(
        modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AdFilterChip(
            adFilterModel = AdFilterModel(
                name = "Favoritter",
                illustration = R.drawable.baseline_favorite_24,
                filter = AdFilter.Favorite
            ),
            onFilterSelected = { onFilterSelected(AdFilter.Favorite) },
            selected = selectedFilter == AdFilter.Favorite,
        )
        AdFilterModel.getFiltersByAdType().forEach { filter ->
            AdFilterChip(
                adFilterModel = filter,
                selected = selectedFilter == filter.filter,
                onFilterSelected = { onFilterSelected(filter.filter) },
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AdFilterChip(
    modifier: Modifier = Modifier,
    adFilterModel: AdFilterModel,
    selected: Boolean,
    onFilterSelected: () -> Unit,
) {
    FilterChip(
        modifier = modifier,
        leadingIcon = {
            Icon(
                painter = painterResource(id = adFilterModel.illustration),
                contentDescription = adFilterModel.name,
                modifier = Modifier.padding(start = 8.dp),
            )
        },
        selected = selected,
        onClick = { onFilterSelected() },
        label = {
            Text(
                text = adFilterModel.name,
                style = Typography.bodyMedium
            )
        },
    )
}

@Preview
@Composable
fun PreviewFilterCard() {
    InvenireTheme {
        AdFilterChip(
            adFilterModel = AdFilterModel(
                name = "Torget",
                illustration = R.drawable.outline_shopping_bag_24,
                filter = AdFilter.Type(AdType.BAP),
            ),
            selected = false,
            onFilterSelected = {},
        )
    }
}
