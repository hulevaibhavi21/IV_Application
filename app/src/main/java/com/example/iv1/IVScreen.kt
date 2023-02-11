package com.example.iv1

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.iv1.ui.*

enum class IVScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    DrugList(title = R.string.start),
    SelectedDrugs(title = R.string.review),
    Results(title = R.string.incomp_res),
    IRCalc(title = R.string.ir_calc)
}

@Composable
fun IVAppBar(
    currentScreen: IVScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title ={ Text(stringResource(id = currentScreen.title)) },
        modifier = modifier,
        navigationIcon ={
            if(canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        }
    )
}

@Composable
fun Start(
    modifier: Modifier = Modifier,
    viewModel: DrugViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = IVScreen.valueOf(
        backStackEntry?.destination?.route ?:IVScreen.Start.name
    )

    Scaffold(
        topBar = {
            IVAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            IVScreen.Start.name,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(route = IVScreen.Start.name) {
                StartScreen(
                    onIRCalcButtonClicked = { navController.navigate(IVScreen.IRCalc.name) },
                    onCompatibilityCheckButtonClicked = {
                        navController.navigate(IVScreen.DrugList.name)
                    }
                )
            }

            composable(route = IVScreen.DrugList.name) {
                SetData(
                    viewModel = viewModel,
                    onDoneBtnClicked = {
                        navController.navigate(IVScreen.SelectedDrugs.name)
                    }
                )
            }

            composable(route = IVScreen.SelectedDrugs.name) {
                ShowSelectedList(
                    drugs = viewModel.getSelectedDrugList(),
                    onCheckBtnClicked = {
                        navController.navigate(IVScreen.Results.name)
                    },
                    onCancelBtnClicked = {
                        cancelAndNavigateToStart(navController, viewModel)
                    },
                    viewModel
                )
            }

            composable(route = IVScreen.Results.name) {
                StartCheck(viewModel)
            }

            composable(route = IVScreen.IRCalc.name) {
                IRScreen()
            }
        }
    }
}


private fun cancelAndNavigateToStart(
    navController: NavHostController,
    viewModel: DrugViewModel
) {
    viewModel.tempList.clear()
    navController.popBackStack(IVScreen.Start.name, inclusive = false)
}
