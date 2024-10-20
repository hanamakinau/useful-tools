package jp.hanamakinau.usefultools.home.usecase

import jp.hanamakinau.usefultools.domain.ToolRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class DisplayToolListUseCase(
    private val toolRepository: ToolRepository,
) {

    private fun display(): Flow<DisplayToolListUseCaseResult> {
        return toolRepository.getToolListFlow()
            .map { tools ->
                DisplayToolListUseCaseResult.Success(
                    tools = tools
                )
            }.onStart<DisplayToolListUseCaseResult> {
                emit(DisplayToolListUseCaseResult.Loading)
            }
    }

    operator fun invoke() = display()
}