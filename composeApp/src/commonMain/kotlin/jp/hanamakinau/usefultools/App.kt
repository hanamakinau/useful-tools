package jp.hanamakinau.usefultools

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import jp.hanamakinau.usefultools.common.ui.RandomTeamingToolsTheme
import jp.hanamakinau.usefultools.di.Dependency
import jp.hanamakinau.usefultools.home.ui.HomeScreen
import jp.hanamakinau.usefultools.navigation.ScreenState
import jp.hanamakinau.usefultools.randomteaming.RandomTeamingScreen
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import useful_tools.composeapp.generated.resources.Res
import useful_tools.composeapp.generated.resources.app_name
import useful_tools.composeapp.generated.resources.back

@Composable
@Preview
fun App() {
    RandomTeamingToolsTheme {
        val navController = rememberNavController()
        val backStack by navController.currentBackStack.collectAsState()
        val hasBackStack by derivedStateOf { backStack.size > 2 }
        val navigationIcon: @Composable () -> Unit = {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(Res.string.back),
                )
            }
        }

        SelectionContainer {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = stringResource(Res.string.app_name),
                            )
                        },
                        navigationIcon = if (hasBackStack) navigationIcon else null,
                    )
                },
            ) {
                NavHost(
                    navController = navController,
                    startDestination = ScreenState.Home.name,
                    modifier = Modifier.fillMaxSize().padding(16.dp),
                ) {
                    composable(ScreenState.Home.name) {
                        HomeScreen(
                            navigateTo = {
                                navController.navigate(it.name)
                            },
                            modifier = Modifier.fillMaxSize(),
                        )
                    }
                    composable(ScreenState.RandomTeaming.name) {
                        RandomTeamingScreen()
                    }
                }

                val screenViewModel = viewModel { Dependency.screenViewModel }
                val uiModel by screenViewModel.uiModel.collectAsState()
                LaunchedEffect(Unit) {
                    snapshotFlow { uiModel.screenState }
                        .drop(1)
                        .distinctUntilChanged()
                        .collect { screenState ->
                            navController.navigate(screenState.name)
                        }
                }
            }
        }
    }
}

