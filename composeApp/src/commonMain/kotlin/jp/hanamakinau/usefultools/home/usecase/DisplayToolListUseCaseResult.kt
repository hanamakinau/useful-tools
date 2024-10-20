package jp.hanamakinau.usefultools.home.usecase

import jp.hanamakinau.usefultools.domain.domainobject.Tool

sealed interface DisplayToolListUseCaseResult {
    data object Loading : DisplayToolListUseCaseResult

    data class Success(
        val tools: List<Tool>,
    ) : DisplayToolListUseCaseResult
}
