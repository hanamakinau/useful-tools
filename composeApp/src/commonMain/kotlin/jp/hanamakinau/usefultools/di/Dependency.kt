package jp.hanamakinau.usefultools.di

import jp.hanamakinau.usefultools.domain.DefaultRandomTeamingSettingRepository
import jp.hanamakinau.usefultools.domain.DefaultToolRepository
import jp.hanamakinau.usefultools.domain.RandomTeamingSettingRepository
import jp.hanamakinau.usefultools.domain.ToolRepository
import jp.hanamakinau.usefultools.home.usecase.DisplayToolListUseCase
import jp.hanamakinau.usefultools.home.viewmodel.HomeViewModel
import jp.hanamakinau.usefultools.navigation.ScreenViewModel
import jp.hanamakinau.usefultools.randomteaming.usecase.DisplayRandomTeamingSettingListUseCase
import jp.hanamakinau.usefultools.randomteaming.usecase.RandomTeamingUseCase
import jp.hanamakinau.usefultools.randomteaming.viewmodel.RandomTeamingViewModel

object Dependency {

    private val toolRepository: ToolRepository by lazy { DefaultToolRepository() }
    private val randomTeamingSettingRepository: RandomTeamingSettingRepository by lazy { DefaultRandomTeamingSettingRepository() }

    private val displayToolListUseCase get() = DisplayToolListUseCase(toolRepository)
    private val displayRandomTeamingSettingListUseCase
        get() = DisplayRandomTeamingSettingListUseCase(randomTeamingSettingRepository)
    private val randomTeamingUseCase get() = RandomTeamingUseCase()

    val screenViewModel get() = ScreenViewModel()
    val homeViewModel get() = HomeViewModel(displayToolListUseCase)
    val randomTeamingViewModel
        get() = RandomTeamingViewModel(
            displayRandomTeamingSettingListUseCase,
            randomTeamingUseCase,
        )
}