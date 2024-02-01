package fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.ui.bluetooth.BluetoothController
import fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.ui.screens.BluetoothScreen
import fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.ui.screens.BoardScreen
import fr.enssat.bluetoothhid.romain_heriteau_maxime_cordier.ui.screens.HomeScreen

@Composable
fun NavGraph(navController: NavHostController, bluetoothController: BluetoothController) {

    NavHost(
        navController = navController,
        startDestination = NavRoute.Home.path
    ) {

        addHomeScreen(navController, this)

        addBluetoothScreen(navController, this, bluetoothController)

        addBoardScreen(navController, this)
    }
}


private fun addBluetoothScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder,
    bluetoothController: BluetoothController,
) {
    navGraphBuilder.composable(route = NavRoute.BluetoothPage.path) {
        BluetoothScreen(
            navigateToBluetooth =  {
                navController.navigate(NavRoute.BluetoothPage.path)
            },
            navigateToHome = {
                navController.navigate(NavRoute.Home.path)
            },
            bluetoothController = bluetoothController
        )
    }
}

private fun addHomeScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = NavRoute.Home.path) {

        HomeScreen(

            navigateToBluetooth =  {
            navController.navigate(NavRoute.BluetoothPage.path)
            },
            navigateToHome = {
                navController.navigate(NavRoute.Home.path)
            },
            navigateToBoard = { boardName ->
                navController.navigate(NavRoute.BoardPage.path+"/$boardName") {
                    popUpTo(NavRoute.Home.path)
                }
            }
        )
    }
}

private fun addBoardScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = NavRoute.BoardPage.path + "/{boardName}") { backStackEntry ->

        BoardScreen(
            backStackEntry.arguments?.getString("boardName") ?: "",
            navigateToBluetooth =  {
                navController.navigate(NavRoute.BluetoothPage.path)
            },
            navigateToHome = {
                navController.navigate(NavRoute.Home.path)
            }
        )
    }
}

