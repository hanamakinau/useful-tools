package jp.hanamakinau.usefultools.randomteaming.usecase

import jp.hanamakinau.usefultools.domain.domainobject.randomteaming.RandomTeamingTeam

data class RandomTeamingUseCaseResult(
    val teams: List<RandomTeamingTeam>,
)
