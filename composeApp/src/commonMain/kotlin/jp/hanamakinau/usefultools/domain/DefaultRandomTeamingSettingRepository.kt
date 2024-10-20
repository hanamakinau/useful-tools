package jp.hanamakinau.usefultools.domain

import jp.hanamakinau.usefultools.domain.domainobject.randomteaming.RandomTeamingLogic
import jp.hanamakinau.usefultools.domain.domainobject.randomteaming.RandomTeamingSetting
import jp.hanamakinau.usefultools.domain.domainobject.randomteaming.RandomTeamingSetting.ValueType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DefaultRandomTeamingSettingRepository : RandomTeamingSettingRepository {
    override fun getRandomTeamingSettingListFlow(): Flow<List<RandomTeamingSetting>> = flow {
        val settings = buildList {
            add(
                RandomTeamingSetting(
                    name = "チーム数",
                    valueType = ValueType.Int,
                    logicType = RandomTeamingLogic.LogicType.RandomTeaming,
                )
            )
        }
        emit(settings)
    }
}