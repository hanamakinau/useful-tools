package jp.hanamakinau.usefultools.domain

import jp.hanamakinau.usefultools.domain.domainobject.randomteaming.RandomTeamingSetting
import kotlinx.coroutines.flow.Flow

interface RandomTeamingSettingRepository {

    fun getRandomTeamingSettingListFlow(): Flow<List<RandomTeamingSetting>>
}