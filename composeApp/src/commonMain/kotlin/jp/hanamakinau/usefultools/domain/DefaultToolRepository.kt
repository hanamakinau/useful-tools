package jp.hanamakinau.usefultools.domain

import jp.hanamakinau.usefultools.domain.domainobject.Tool
import jp.hanamakinau.usefultools.domain.domainobject.ToolType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DefaultToolRepository : ToolRepository {
    override fun getToolListFlow(): Flow<List<Tool>> = flow {
        val toolList = buildList {
            add(Tool(name = "チーム分け", type = ToolType.RandomTeaming))
        }
        emit(toolList)
    }
}