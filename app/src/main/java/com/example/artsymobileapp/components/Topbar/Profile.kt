package com.example.artsymobileapp.components.Topbar

import android.content.Context
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.artsymobileapp.components.SharedPreferences.readAuthenticated
import com.example.artsymobileapp.components.SharedPreferences.readUser
import com.example.artsymobileapp.components.network.ViewModel.ArtsyViewModel
import com.example.artsymobileapp.components.screens.screens

val avatar = Modifier.clip(CircleShape)

fun logOut(context: Context, navController: NavController, viewModel: ArtsyViewModel) {
    viewModel.logout(context = context, navController = navController)
}

fun deleteAccount(
    context: Context,
    navController: NavController,
    viewModel: ArtsyViewModel,
    email: String
) {
    viewModel.deleteAccount(context = context, navController = navController, email = email)
}

@Composable
fun Profile(navController: NavController, viewModel: ArtsyViewModel) {
    val context = LocalContext.current
    val authenticated = readAuthenticated(context = context)
    val user = readUser(context = context)
    var expanded by remember { mutableStateOf(false) }

    if (authenticated) {
        IconButton(onClick = { expanded = !expanded }) {
            AsyncImage(
                model = user?.profileUrl,
                contentDescription = "Profile Image",
                modifier = avatar
            )
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(
                text = { Text(text = "Log out") },
                onClick = {
                    logOut(
                        context = context,
                        navController = navController,
                        viewModel = viewModel
                    )
                })
            DropdownMenuItem(
                text = { Text(text = "Delete account", color = Color.Red) },
                onClick = {
                    deleteAccount(
                        context = context,
                        navController = navController,
                        viewModel = viewModel,
                        email = user?.email ?: ""
                    )
                })
        }
    } else {
        IconButton(onClick = { navController.navigate(route = screens.Login.name) }) {
            Icon(
                Icons.Default.Person, contentDescription = "Profile"
            )

        }
    }
}