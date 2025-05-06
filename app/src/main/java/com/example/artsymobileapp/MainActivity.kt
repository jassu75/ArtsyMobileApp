package com.example.artsymobileapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.artsymobileapp.components.App
import com.example.artsymobileapp.components.network.ArtsyAPI
import com.example.artsymobileapp.components.network.ViewModel.ArtsyViewModel
import com.example.artsymobileapp.ui.theme.ArtsyMobileAppTheme

class MainActivity : ComponentActivity() {
    private val viewModel: ArtsyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition {
            !viewModel.isInitialised
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ArtsyAPI.init(applicationContext)
        setContent {
            ArtsyMobileAppTheme {
                App(viewModel = viewModel)

            }
        }
    }
}