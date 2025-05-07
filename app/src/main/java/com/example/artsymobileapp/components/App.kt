package com.example.artsymobileapp.components

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.artsymobileapp.components.screens.Homepage
import com.example.artsymobileapp.components.network.ViewModel.ArtsyViewModel
import com.example.artsymobileapp.components.screens.ArtistDetails
import com.example.artsymobileapp.components.screens.Login
import com.example.artsymobileapp.components.screens.Register
import com.example.artsymobileapp.components.screens.screens

@Composable
fun App(viewModel: ArtsyViewModel) {
    val navController = rememberNavController()

    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.snackBarMessage.collect { text ->
            snackBarHostState.showSnackbar(text)
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }

    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = screens.Homepage.name,
        ) {
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
                    viewModel = viewModel,
                    artistId = backStackEntry.arguments?.getString("artistId") ?: "",
                    artistName = backStackEntry.arguments?.getString("artistName") ?: "",
                    navController = navController
                )
            }

            composable(
                route = screens.Login.name
            ) {
                Login(navController = navController, viewModel = viewModel)
            }

            composable(
                route = screens.Register.name
            ) {
                Register(navController = navController, viewModel = viewModel)
            }
        }
    }

}