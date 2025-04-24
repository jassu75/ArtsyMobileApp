package com.example.artsymobileapp.components.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.navigation.NavController

import com.example.artsymobileapp.components.Topbar.LoginTopBar
import com.example.artsymobileapp.components.UserControl.LoginControl.LoginInput
import com.example.artsymobileapp.components.network.ViewModel.ArtsyViewModel


@Composable
fun Login(navController: NavController,viewModel: ArtsyViewModel) {

    Scaffold(
        topBar = { LoginTopBar(navController=navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
                LoginInput(navController=navController,viewModel=viewModel)
        }
    }
}