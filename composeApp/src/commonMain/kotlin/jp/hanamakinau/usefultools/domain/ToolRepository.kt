package jp.hanamakinau.usefultools.domain

import jp.hanamakinau.usefultools.domain.domainobject.Tool
import kotlinx.coroutines.flow.Flow

interface ToolRepository {

    fun getToolListFlow(): Flow<List<Tool>>
}