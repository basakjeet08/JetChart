package dev.anirban.jetchart.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.anirban.jetchart.data.repo.LinearMockDataRepo
import dev.anirban.jetchart.view.screens.CircularChartUIScreen
import dev.anirban.jetchart.view.screens.HomeScreen
import dev.anirban.jetchart.view.screens.LinearChartUIScreen


const val HOME_ROUTE = "home-route"
const val LINEAR_CHART_UI_ROUTE = "linear-chart-ui-route"
const val CIRCULAR_CHART_UI_ROUTE = "circular-chart-ui-route"

@Composable
fun MainNavGraph(navHostController: NavHostController) {

    NavHost(navController = navHostController, startDestination = HOME_ROUTE) {

        composable(HOME_ROUTE) {
            HomeScreen {
                navHostController.navigate(it)
            }
        }

        composable(LINEAR_CHART_UI_ROUTE) {

            // Linear Chart Data Sample
            var linearData by remember {
                mutableStateOf(LinearMockDataRepo.generateLinearMockDataResponse())
            }

            // Calling the Linear Chart Data UI
            LinearChartUIScreen(mockLinearData = linearData) {
                linearData = LinearMockDataRepo.generateLinearMockDataResponse()
            }
        }


        composable(CIRCULAR_CHART_UI_ROUTE) {
            CircularChartUIScreen()
        }
    }
}