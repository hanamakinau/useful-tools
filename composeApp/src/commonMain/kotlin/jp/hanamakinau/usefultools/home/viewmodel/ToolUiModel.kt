package jp.hanamakinau.usefultools.home.viewmodel

import jp.hanamakinau.usefultools.domain.domainobject.ToolType

data class ToolUiModel(
    val name: String,
    val type: ToolType,
)
