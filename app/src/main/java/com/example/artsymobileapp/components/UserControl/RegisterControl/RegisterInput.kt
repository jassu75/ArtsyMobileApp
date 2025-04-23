package com.example.artsymobileapp.components.UserControl.RegisterControl


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

val register_container = Modifier
    .fillMaxSize()
    .padding(20.dp)
val register_items = Modifier.fillMaxWidth()

@Composable
fun RegisterInput(navController: NavController) {
    var fullName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Box(modifier = register_container, contentAlignment = Alignment.Center) {
        Column(
            modifier = register_items,
            verticalArrangement = Arrangement.spacedBy(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = { Text(text = "Enter full name") },
                placeholder = { Text(text = "Enter full name") },
                modifier = register_items,
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Enter email") },
                placeholder = { Text(text = "Enter email") },
                modifier = register_items,
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Enter password") },
                placeholder = { Text(text = "Enter password") },
                modifier = register_items,
            )
            Button(modifier = register_items, onClick = {}) {
                Text(text = "Register")
            }

            Text(
                text = DirectToLoginText(navController = navController),
            )

        }
    }


}