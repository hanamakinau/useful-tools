package jp.hanamakinau.usefultools.randomteaming.usecase

import jp.hanamakinau.usefultools.domain.RandomTeamingSettingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class DisplayRandomTeamingSettingListUseCase(
    private val randomTeamingSettingRepository: RandomTeamingSettingRepository,
) {

    private fun display(): Flow<DisplayRandomTeamingSettingListUseCaseResult> =
        randomTeamingSettingRepository.getRandomTeamingSettingListFlow()
            .map { settingList ->
                DisplayRandomTeamingSettingListUseCaseResult.Success(settingList)
            }.onStart<DisplayRandomTeamingSettingListUseCaseResult> {
                emit(DisplayRandomTeamingSettingListUseCaseResult.Loading)
            }

    operator fun invoke() = display()
}