package no.app.invenire

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import no.app.invenire.ui.screens.AdsScreen
import no.app.invenire.ui.theme.InvenireTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val viewModel = hiltViewModel<MainViewModel>()
            val state = viewModel.state.collectAsState(initial = ViewState())

            InvenireTheme {
                Scaffold {
                    AdsScreen(
                        modifier = Modifier.padding(it),
                        state = state.value,
                        onFilterSelected = viewModel::onFilterSelected,
                        onRefreshed = viewModel::refreshAds,
                        onItemSelected = viewModel::onItemSelected,
                    )
                }
            }
        }
    }
}
