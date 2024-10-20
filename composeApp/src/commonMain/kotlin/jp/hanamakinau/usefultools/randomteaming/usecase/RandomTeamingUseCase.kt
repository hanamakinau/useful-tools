package jp.hanamakinau.usefultools.randomteaming.usecase

import jp.hanamakinau.usefultools.domain.domainobject.randomteaming.RandomTeamingLogic
import jp.hanamakinau.usefultools.domain.domainobject.randomteaming.RandomTeamingMember
import jp.hanamakinau.usefultools.domain.domainobject.randomteaming.RandomTeamingSetting
import jp.hanamakinau.usefultools.domain.domainobject.randomteaming.RandomTeamingSettings
import jp.hanamakinau.usefultools.domain.domainobject.randomteaming.RandomTeamingTeam

class RandomTeamingUseCase {

    private val randomTeamingLogic by lazy { RandomTeamingLogic() }

    private fun randomTeaming(
        inputMember: String,
        settings: RandomTeamingSettings,
    ): RandomTeamingUseCaseResult {
        val members = parseInputMember(inputMember)
        var teams = listOf<RandomTeamingTeam>()
        settings.settings.forEach { setting ->
            teams = executeRandomTeamingLogic(members, setting.key, setting.value)
        }
        return RandomTeamingUseCaseResult(teams)
    }

    operator fun invoke(
        inputMember: String,
        settings: RandomTeamingSettings,
    ) = randomTeaming(inputMember, settings)

    private fun parseInputMember(inputMember: String): List<RandomTeamingMember> {
        return inputMember
            .split("\n")
            .flatMap { line -> line.split(",") }
            .map { name -> RandomTeamingMember(name) }
    }

    private fun executeRandomTeamingLogic(
        members: List<RandomTeamingMember>,
        setting: RandomTeamingSetting,
        value: Any,
    ): List<RandomTeamingTeam> = try {
        checkValueType(setting.valueType, value)
        when (setting.logicType) {
            RandomTeamingLogic.LogicType.RandomTeaming ->
                randomTeamingLogic.randomTeaming(members, value as Int)
        }
    } catch (e: IllegalArgumentException) {
        emptyList()
    }

    private fun checkValueType(
        valueType: RandomTeamingSetting.ValueType,
        value: Any,
    ) {
        when (valueType) {
            RandomTeamingSetting.ValueType.Int -> {
                if (value !is Int) throw IllegalArgumentException("Value must be Int")
            }
        }
    }
}