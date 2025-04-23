package com.example.artsymobileapp.components.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.navigation.NavController

import com.example.artsymobileapp.components.Topbar.RegisterTopBar
import com.example.artsymobileapp.components.UserControl.RegisterControl.RegisterInput


@Composable
fun Register(navController: NavController) {

    Scaffold(
        topBar = { RegisterTopBar(navController=navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            RegisterInput(navController=navController)
        }
    }
}