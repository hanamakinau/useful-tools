package jp.hanamakinau.usefultools.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import jp.hanamakinau.usefultools.di.Dependency
import jp.hanamakinau.usefultools.home.viewmodel.HomeUiModel
import jp.hanamakinau.usefultools.home.viewmodel.HomeViewModel
import jp.hanamakinau.usefultools.navigation.ScreenState

@Composable
fun HomeScreen(
    navigateTo: (ScreenState) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel { Dependency.homeViewModel },
) {
    val uiModel by viewModel.uiModel.collectAsState()
    val homeUiModel = uiModel
    when (homeUiModel) {
        HomeUiModel.Loading -> Unit
        is HomeUiModel.Contents -> {
            ToolList(
                toolList = homeUiModel.toolList,
                navigateTo = navigateTo,
                modifier = modifier,
            )
        }
    }

    LaunchedEffect(Unit) {
        viewModel.onCreateScreen()
    }
}

