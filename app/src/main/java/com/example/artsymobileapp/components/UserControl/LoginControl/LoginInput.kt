package com.example.artsymobileapp.components.UserControl.LoginControl

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

val login_container = Modifier
    .fillMaxSize()
    .padding(20.dp)
val login_items = Modifier.fillMaxWidth()

@Composable
fun LoginInput(navController: NavController) {
    var fullName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Box(modifier = login_container, contentAlignment = Alignment.Center) {
        Column(
            modifier = login_items,
            verticalArrangement = Arrangement.spacedBy(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Enter email") },
                placeholder = { Text(text = "Enter email") },
                modifier = login_items,
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Enter password") },
                placeholder = { Text(text = "Enter password") },
                modifier = login_items,
            )
            Button(modifier = login_items, onClick = {}) {
                Text(text = "Login")
            }

            Text(
                text = DirectToRegisterText(navController = navController),
            )

        }
    }


}