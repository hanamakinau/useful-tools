package jp.hanamakinau.usefultools.randomteaming.usecase

import jp.hanamakinau.usefultools.domain.domainobject.randomteaming.RandomTeamingSetting

sealed interface DisplayRandomTeamingSettingListUseCaseResult {

    data object Loading : DisplayRandomTeamingSettingListUseCaseResult

    data class Success(
        val settingList: List<RandomTeamingSetting>,
    ) : DisplayRandomTeamingSettingListUseCaseResult
}