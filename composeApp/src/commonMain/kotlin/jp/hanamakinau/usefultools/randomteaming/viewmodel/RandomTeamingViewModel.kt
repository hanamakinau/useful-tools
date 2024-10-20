package jp.hanamakinau.usefultools.randomteaming.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.hanamakinau.usefultools.domain.domainobject.randomteaming.RandomTeamingSetting
import jp.hanamakinau.usefultools.domain.domainobject.randomteaming.RandomTeamingSettings
import jp.hanamakinau.usefultools.randomteaming.usecase.DisplayRandomTeamingSettingListUseCase
import jp.hanamakinau.usefultools.randomteaming.usecase.DisplayRandomTeamingSettingListUseCaseResult
import jp.hanamakinau.usefultools.randomteaming.usecase.RandomTeamingUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class RandomTeamingViewModel(
    private val displayRandomTeamingSettings: DisplayRandomTeamingSettingListUseCase,
    private val randomTeaming: RandomTeamingUseCase,
) : ViewModel() {

    private val inputMembers = MutableStateFlow("")
    private val inputSettings = MutableStateFlow(RandomTeamingSettingsUiModel())

    private var displayJob: Job? = null

    val uiModel = combine(
        inputMembers,
        inputSettings,
    ) { inputMembers, inputSettings ->
        RandomTeamingUiModel(
            inputMembers = inputMembers,
            settings = inputSettings,
            teams = RandomTeamingTeamsUiModel(
                randomTeaming(inputMembers, inputSettings.toRandomTeamingSettings()).teams,
            )
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = RandomTeamingUiModel(),
    )

    fun onCreateScreen() {
        displayJob?.cancel()
        displayJob = displayRandomTeamingSettings()
            .onEach { result ->
                when (result) {
                    DisplayRandomTeamingSettingListUseCaseResult.Loading -> Unit
                    is DisplayRandomTeamingSettingListUseCaseResult.Success -> {
                        this.inputSettings.update {
                            it.updateWith(result.settingList)
                        }
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    fun onInputMembersChanged(inputMembers: String) {
        this.inputMembers.value = inputMembers
    }

    fun onInputSettingChanged(setting: RandomTeamingSetting, input: String) {
        this.inputSettings.update { it.updateWith(setting, input) }
    }

    private fun RandomTeamingSettingsUiModel.toRandomTeamingSettings(): RandomTeamingSettings {
        return RandomTeamingSettings(
            settings = inputSettings.associate { setting ->
                val key = setting.setting
                val value = when (setting) {
                    is RandomTeamingInputSettingUiModel.IntSetting ->
                        setting.setting.valueType.getValueOrDefault(value = setting.input)
                }
                key to value
            }
        )
    }
}