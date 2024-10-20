package jp.hanamakinau.usefultools.randomteaming.viewmodel

import jp.hanamakinau.usefultools.domain.domainobject.randomteaming.RandomTeamingSetting

data class RandomTeamingSettingsUiModel(
    val inputSettings: List<RandomTeamingInputSettingUiModel> = emptyList(),
) {
    fun updateWith(settings: List<RandomTeamingSetting>): RandomTeamingSettingsUiModel {
        val newInputSettings = buildList {
            settings.forEach { setting ->
                val inputSetting = inputSettings.find { it.setting == setting }
                if (inputSetting != null) {
                    add(inputSetting)
                } else {
                    when (setting.valueType) {
                        RandomTeamingSetting.ValueType.Int -> {
                            add(
                                RandomTeamingInputSettingUiModel.IntSetting(setting, "")
                            )
                        }
                    }
                }
            }
        }
        return copy(inputSettings = newInputSettings)
    }

    fun updateWith(
        setting: RandomTeamingSetting,
        input: String,
    ): RandomTeamingSettingsUiModel {
        val newInputSettings = inputSettings.map { inputSetting ->
            if (inputSetting.setting == setting) {
                when (inputSetting.setting.valueType) {
                    RandomTeamingSetting.ValueType.Int -> {
                        RandomTeamingInputSettingUiModel.IntSetting(setting, input)
                    }
                }
            } else {
                inputSetting
            }
        }
        return copy(inputSettings = newInputSettings)
    }
}
