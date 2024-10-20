package jp.hanamakinau.usefultools.domain.domainobject.randomteaming

class RandomTeamingLogic {

    fun randomTeaming(
        members: List<RandomTeamingMember>,
        teamCount: Int,
    ): List<RandomTeamingTeam> {
        val shuffledMembers = members.shuffled()
        val teamMemberCount = try {
            calculateTeamMemberCount(shuffledMembers, teamCount)
        } catch (e: IllegalArgumentException) {
            return emptyList()
        }
        val teams = shuffledMembers
            .chunked(teamMemberCount)
            .mapIndexed { index, teamMembers ->
                RandomTeamingTeam(
                    name = "チーム ${index + 1}",
                    members = teamMembers,
                )
            }
        return teams
    }

    private fun calculateTeamMemberCount(
        members: List<RandomTeamingMember>,
        teamCount: Int,
    ): Int {
        if (teamCount == 0) throw IllegalArgumentException("teamCount must be greater than 0")
        if (members.size <= teamCount) return 1
        return members.size / teamCount
    }

    enum class LogicType {
        RandomTeaming,
    }
}