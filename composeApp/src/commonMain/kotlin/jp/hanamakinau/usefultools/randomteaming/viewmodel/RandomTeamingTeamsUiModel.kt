package jp.hanamakinau.usefultools.randomteaming.viewmodel

import jp.hanamakinau.usefultools.domain.domainobject.randomteaming.RandomTeamingTeam

data class RandomTeamingTeamsUiModel(
    val teams: List<RandomTeamingTeam> = emptyList(),
)
