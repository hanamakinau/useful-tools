package jp.hanamakinau.usefultools.home.viewmodel

sealed interface HomeUiModel {

    data object Loading : HomeUiModel

    data class Contents(
        val toolList: ToolListUiModel,
    ) : HomeUiModel
}
