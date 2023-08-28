package no.app.invenire

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import no.app.invenire.domain.AdItemList
import no.app.invenire.repository.AdRepository
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

    private fun getAds() {
        viewModelScope.launch {
            val result = adRepository.getAds()

            if (result.isSuccessful) {
                val adItems = result.body()?.items ?: emptyList()

                _state.update {
                    it.copy(
                        ads = adItems,
                        isLoading = false,
                    )
                }
            } else {
                _state.update {
                    it.copy(
                        isLoading = false,
                    )
                }
            }
        }
    }
}

data class ViewState(
    val ads: AdItemList = emptyList(),
    val isLoading: Boolean = true,
)
