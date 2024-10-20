package jp.hanamakinau.usefultools.randomteaming

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import jp.hanamakinau.usefultools.di.Dependency
import jp.hanamakinau.usefultools.domain.domainobject.randomteaming.RandomTeamingSetting
import jp.hanamakinau.usefultools.randomteaming.viewmodel.RandomTeamingInputSettingUiModel
import jp.hanamakinau.usefultools.randomteaming.viewmodel.RandomTeamingSettingsUiModel
import jp.hanamakinau.usefultools.randomteaming.viewmodel.RandomTeamingTeamsUiModel
import jp.hanamakinau.usefultools.randomteaming.viewmodel.RandomTeamingViewModel
import org.jetbrains.compose.resources.stringResource
import useful_tools.composeapp.generated.resources.Res
import useful_tools.composeapp.generated.resources.random_teaming_input_members
import useful_tools.composeapp.generated.resources.random_teaming_input_members_description
import useful_tools.composeapp.generated.resources.random_teaming_result
import useful_tools.composeapp.generated.resources.random_teaming_select_settings
import useful_tools.composeapp.generated.resources.random_teaming_select_settings_description


@Composable
fun RandomTeamingScreen(
    modifier: Modifier = Modifier,
    viewModel: RandomTeamingViewModel = viewModel { Dependency.randomTeamingViewModel },
) {
    val uiModel by viewModel.uiModel.collectAsState()
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        inputMembersSection(
            inputMembers = uiModel.inputMembers,
            onInputMembersChange = viewModel::onInputMembersChanged,
        )

        sectionSpacer()

        selectSettingsSection(
            settings = uiModel.settings,
            onInputSettingChanged = viewModel::onInputSettingChanged,
        )

        sectionSpacer()

        resultSection(
            teams = uiModel.teams,
        )
    }

    LaunchedEffect(Unit) {
        viewModel.onCreateScreen()
    }
}

private fun LazyListScope.inputMembersSection(
    inputMembers: String,
    onInputMembersChange: (String) -> Unit,
) {
    item {
        Text(
            text = stringResource(Res.string.random_teaming_input_members),
            style = MaterialTheme.typography.h5,
        )
    }
    item {
        Text(
            text = stringResource(Res.string.random_teaming_input_members_description),
            style = MaterialTheme.typography.body1,
        )
    }
    item {
        TextField(
            value = inputMembers,
            onValueChange = onInputMembersChange,
        )
    }
}

private fun LazyListScope.selectSettingsSection(
    settings: RandomTeamingSettingsUiModel,
    onInputSettingChanged: (RandomTeamingSetting, String) -> Unit,
) {
    item {
        Text(
            text = stringResource(Res.string.random_teaming_select_settings),
            style = MaterialTheme.typography.h5,
        )
    }
    item {
        Text(
            text = stringResource(Res.string.random_teaming_select_settings_description),
            style = MaterialTheme.typography.body1,
        )
    }
    items(settings.inputSettings) { setting ->
        when (setting) {
            is RandomTeamingInputSettingUiModel.IntSetting -> IntSettingItem(
                setting = setting,
                onInputSettingChange = onInputSettingChanged,
            )
        }
    }
}

private fun LazyListScope.resultSection(
    teams: RandomTeamingTeamsUiModel,
) {
    item {
        Text(
            text = stringResource(Res.string.random_teaming_result),
            style = MaterialTheme.typography.h5,
        )
    }

    items(teams.teams) { team ->
        Column {
            Text(
                text = team.name + "\n",
                style = MaterialTheme.typography.h6,
                maxLines = 1,
            )
            team.members.forEach { member ->
                Text(
                    text = member.name,
                    style = MaterialTheme.typography.body1,
                )
            }
        }
    }
}

private fun LazyListScope.sectionSpacer() {
    item {
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
private fun IntSettingItem(
    setting: RandomTeamingInputSettingUiModel.IntSetting,
    onInputSettingChange: (RandomTeamingSetting, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = setting.name,
            style = MaterialTheme.typography.h6,
        )
        TextField(
            value = setting.input,
            onValueChange = {
                onInputSettingChange(setting.setting, it)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
        )
    }
}
