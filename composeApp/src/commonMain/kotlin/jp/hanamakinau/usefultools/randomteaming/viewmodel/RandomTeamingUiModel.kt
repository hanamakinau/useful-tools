package jp.hanamakinau.usefultools.randomteaming.viewmodel

data class RandomTeamingUiModel(
    val inputMembers: String = "",
    val settings: RandomTeamingSettingsUiModel = RandomTeamingSettingsUiModel(),
    val teams: RandomTeamingTeamsUiModel = RandomTeamingTeamsUiModel(),
)