package com.example.artsymobileapp.components

import ArtistSearchBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.artsymobileapp.components.Topbar.TopBar
import com.example.artsymobileapp.components.network.ViewModel.ArtsyViewModel

@Composable
fun App() {
    val viewModel: ArtsyViewModel = viewModel()

    TopBar(viewModel=viewModel)
}