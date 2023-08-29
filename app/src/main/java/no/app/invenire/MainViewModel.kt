package no.app.invenire

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import no.app.invenire.repository.AdRepository
import no.app.invenire.ui.components.AdFilter
import no.app.invenire.ui.models.Ads
import no.app.invenire.ui.models.toUiModels
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val adRepository: AdRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(ViewState())
    val state: Flow<ViewState> = _state

    init {
        getAds()
    }

    fun onFilterSelected(filter: AdFilter) {
        _state.update { currentState ->
            currentState.copy(
                selectedFilter = if (currentState.selectedFilter == filter) null else filter
            )
        }
    }

    fun onItemSelected(itemId: String) {
        _state.update { currentState ->
            currentState.copy(
                allAds = currentState.allAds.map { ad ->
                    if (ad.id == itemId) {
                        ad.copy(isFavorite = !ad.isFavorite)
                    } else {
                        ad
                    }
                },
            )
        }
    }

    private fun getAds() {
        viewModelScope.launch {
            val result = adRepository.getAds()

            if (result.isSuccessful) {
                val adItems = result.body()?.items?.toUiModels() ?: emptyList()

                _state.update { currentState ->
                    currentState.copy(
                        allAds = adItems,
                        isLoading = false,
                    )
                }
            } else {
                _state.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                    )
                }
            }
        }
    }
}

data class ViewState(
    val allAds: Ads = emptyList(),
    val favoriteAds: Ads = emptyList(),
    val selectedFilter: AdFilter? = null,
    val isLoading: Boolean = true,
) {
    val filteredAds: Ads
        get() = if (selectedFilter != null) {
            when (selectedFilter) {
                AdFilter.Favorite -> allAds.filter { ad -> ad.isFavorite }
                is AdFilter.Type -> allAds.filter { ad -> ad.adType == selectedFilter.type }
            }
        } else {
            allAds
        }
}
