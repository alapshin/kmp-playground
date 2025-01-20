package dev.bitbakery.boilerplate.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.Flow
import me.tatarka.inject.annotations.Inject

@Inject
class HomePresenterImpl : HomePresenter {
    @Composable
    override fun state(events: Flow<HomeEvent>): HomeState {
        var progress: Boolean by remember { mutableStateOf(false) }

        return HomeState(progress = progress)
    }
}
