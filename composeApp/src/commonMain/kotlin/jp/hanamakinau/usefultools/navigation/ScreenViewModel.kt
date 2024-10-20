package jp.hanamakinau.usefultools.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ScreenViewModel : ViewModel() {

    private val screenState = MutableStateFlow(ScreenState.Home)
    val uiModel = screenState
        .map { ScreenUiModel(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), ScreenUiModel())

    fun navigateTo(screenState: ScreenState) {
        this.screenState.value = screenState
    }
}