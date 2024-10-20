package jp.hanamakinau.usefultools.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.hanamakinau.usefultools.domain.domainobject.Tool
import jp.hanamakinau.usefultools.home.usecase.DisplayToolListUseCase
import jp.hanamakinau.usefultools.home.usecase.DisplayToolListUseCaseResult
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val displayToolList: DisplayToolListUseCase,
) : ViewModel() {

    private val homeUiModel = MutableStateFlow<HomeUiModel>(HomeUiModel.Loading)
    val uiModel = homeUiModel.asStateFlow()

    private var displayJob: Job? = null

    fun onCreateScreen() {
        displayJob?.cancel()
        displayJob = viewModelScope.launch {
            displayToolList().collect { result ->
                homeUiModel.value = when (result) {
                    is DisplayToolListUseCaseResult.Loading -> HomeUiModel.Loading
                    is DisplayToolListUseCaseResult.Success -> HomeUiModel.Contents(result.tools.toToolListUiModel())
                }
            }
        }
    }
}

private fun List<Tool>.toToolListUiModel(): ToolListUiModel {
    val items = map { it.toToolUiModel() }
    return ToolListUiModel(items)
}

private fun Tool.toToolUiModel(): ToolUiModel {
    return ToolUiModel(
        name = name,
        type = type,
    )
}