package jp.hanamakinau.usefultools.home.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import jp.hanamakinau.usefultools.domain.domainobject.ToolType
import jp.hanamakinau.usefultools.home.viewmodel.ToolListUiModel
import jp.hanamakinau.usefultools.home.viewmodel.ToolUiModel
import jp.hanamakinau.usefultools.navigation.ScreenState

@Composable
fun ToolList(
    toolList: ToolListUiModel,
    navigateTo: (ScreenState) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(toolList.items) { item ->
            ToolItem(
                item = item,
                navigateTo = navigateTo,
            )
        }
    }
}

@Composable
fun ToolItem(
    item: ToolUiModel,
    navigateTo: (ScreenState) -> Unit,
    modifier: Modifier = Modifier,
) {
    Text(
        text = item.name,
        modifier = modifier.clickable { navigateTo(item.type.toScreenState()) },
    )
}

private fun ToolType.toScreenState() = when (this) {
    ToolType.RandomTeaming -> ScreenState.RandomTeaming
}
