package com.example.artsymobileapp.components.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavController

import com.example.artsymobileapp.components.Topbar.RegisterTopBar
import com.example.artsymobileapp.components.UserControl.RegisterControl.RegisterInput
import com.example.artsymobileapp.components.network.ViewModel.ArtsyViewModel


@Composable
fun Register(navController: NavController,viewModel: ArtsyViewModel) {

    Scaffold(
        topBar = { RegisterTopBar(navController=navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            RegisterInput(viewModel = viewModel, navController=navController)
        }
    }
}