package no.app.invenire

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import no.app.invenire.repository.implementation.AdRepository
import no.app.invenire.ui.components.AdFilter
import no.app.invenire.ui.models.ui.Ads
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
        viewModelScope.launch {
            val selectedAd = _state.value.allAds.firstOrNull { it.id == itemId } ?: return@launch

            if (selectedAd.isFavorite) {
                adRepository.removeAd(itemId)
            } else {
                adRepository.insertAd(selectedAd)
            }

            _state.update { currentState ->
                currentState.copy(
                    allAds = currentState.allAds.map { ad ->
                        if (ad.id == itemId) ad.copy(isFavorite = selectedAd.isFavorite.not())
                        else ad
                    }
                )
            }
        }
    }

    fun refreshAds() {
        _state.update { currentState ->
            currentState.copy(
                allAds = emptyList(),
                refreshing = true,
            )
        }
        getAds()
    }

    private fun getAds() {
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(
                    allAds = adRepository.getAds(),
                    refreshing = false,
                )
            }
        }
    }
}

data class ViewState(
    val allAds: Ads = emptyList(),
    val selectedFilter: AdFilter? = null,
    val refreshing: Boolean = true,
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
