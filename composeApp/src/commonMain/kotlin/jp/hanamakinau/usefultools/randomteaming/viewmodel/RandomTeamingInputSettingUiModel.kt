package jp.hanamakinau.usefultools.randomteaming.viewmodel

import jp.hanamakinau.usefultools.domain.domainobject.randomteaming.RandomTeamingSetting

sealed interface RandomTeamingInputSettingUiModel {

    val setting: RandomTeamingSetting
    val name: String
        get() = setting.name

    data class IntSetting(
        override val setting: RandomTeamingSetting,
        val input: String,
    ) : RandomTeamingInputSettingUiModel
}
