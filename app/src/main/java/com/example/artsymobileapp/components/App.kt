package com.example.artsymobileapp.components

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.artsymobileapp.components.screens.Homepage
import com.example.artsymobileapp.components.network.ViewModel.ArtsyViewModel
import com.example.artsymobileapp.components.screens.ArtistDetails
import com.example.artsymobileapp.components.screens.screens

@Composable
fun App() {
    val viewModel: ArtsyViewModel = viewModel()
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = screens.Homepage.name) {
        composable(route = screens.Homepage.name) {
            Homepage(viewModel = viewModel, navController = navController)
        }
        composable(
            route = "${screens.ArtistDetails.name}/{artistId}/{artistName}",
            arguments = listOf(
                navArgument(name = "artistId") {
                    type = NavType.StringType
                },
                navArgument(name = "artistName") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            ArtistDetails(
                backStackEntry.arguments?.getString("artistId") ?: "",
                backStackEntry.arguments?.getString("artistName") ?: ""
            )
        }
    }
}