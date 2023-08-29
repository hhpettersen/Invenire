package no.app.invenire.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import no.app.invenire.ViewState
import no.app.invenire.ui.components.AdFilter
import no.app.invenire.ui.components.RowOfAdFilters
import no.app.invenire.ui.components.AdList

@Composable
fun AdsScreen(
    state: ViewState,
    onFilterSelected: (AdFilter) -> Unit,
    onRefreshed: () -> Unit,
    onItemSelected: (String) -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
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
