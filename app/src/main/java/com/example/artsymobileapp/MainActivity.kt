package com.example.artsymobileapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.artsymobileapp.components.App
import com.example.artsymobileapp.ui.theme.ArtsyMobileAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtsyMobileAppTheme {
                App()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    ArtsyMobileAppTheme {
        App()
    }
}