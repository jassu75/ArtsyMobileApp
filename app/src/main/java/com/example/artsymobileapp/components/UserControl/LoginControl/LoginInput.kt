package com.example.artsymobileapp.components.UserControl.LoginControl

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.artsymobileapp.components.network.ViewModel.ArtsyViewModel
import com.example.artsymobileapp.components.network.types.userType.loginUserType

private val login_container = Modifier
    .fillMaxSize()
    .padding(20.dp)
private val login_items = Modifier.fillMaxWidth()


@Composable
fun LoginInput(navController: NavController, viewModel: ArtsyViewModel) {
    var email by rememberSaveable { mutableStateOf("") }
    var emailError by rememberSaveable { mutableStateOf(false) }
    var emailFirstFocus by rememberSaveable { mutableStateOf(false) }
    var emailErrorText by rememberSaveable { mutableStateOf("Email cannot be empty") }

    var password by rememberSaveable { mutableStateOf("") }
    var passwordError by rememberSaveable { mutableStateOf(false) }
    var passwordFirstFocus by rememberSaveable { mutableStateOf(false) }
    val passwordErrorText by rememberSaveable { mutableStateOf("Password cannot be empty") }

    var loginError by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current
    var loading by rememberSaveable { mutableStateOf(false) }

    Box(modifier = login_container, contentAlignment = Alignment.Center) {
        Column(
            modifier = login_items,
            verticalArrangement = Arrangement.spacedBy(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column() {
                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        if (email.isEmpty()) {
                            emailError = true
                            emailErrorText = "Email cannot be empty"
                        } else {
                            emailError = false
                        }
                        if (loginError) {
                            loginError = false
                        }
                    },
                    label = { Text(text = "Enter email") },
                    placeholder = { Text(text = "Enter email") },
                    isError = emailError,
                    modifier = login_items.onFocusChanged { focusState ->
                        if (!emailFirstFocus && focusState.isFocused) {
                            emailFirstFocus = true
                        }

                    },
                )
                if (emailFirstFocus && emailError) {
                    Text(text = emailErrorText, color = Color.Red)
                }
            }
            Column() {
                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        if (password.isEmpty()) {
                            passwordError = true
                        } else {
                            passwordError = false
                        }
                        if (loginError) {
                            loginError = false
                        }
                    },
                    label = { Text(text = "Enter password") },
                    placeholder = { Text(text = "Enter password") },
                    modifier = login_items.onFocusChanged { focusState ->
                        if (!passwordFirstFocus && focusState.isFocused) {
                            passwordFirstFocus = true
                        }


                    },
                )
                if (passwordFirstFocus && passwordError) {
                    Text(text = passwordErrorText, color = Color.Red)
                }
            }

            Column() {
                Button(
                    modifier = login_items,
                    enabled = !loading,
                    onClick = {
                        val emailRegex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$")

                        if (!emailRegex.matches(email)) {
                            emailErrorText = "Invalid email format"
                            emailError = true
                        } else {
                            val userLoginData = loginUserType(
                                email = email,
                                password = password
                            )
                            viewModel.loginUser(
                                setLoading = { loading = it },
                                userLoginData = userLoginData,
                                navController = navController,
                                setLoginError = { loginError = it }
                            )

                        }
                    }
                ) {
                    if (loading) {
                        CircularProgressIndicator(
                            color = Color.White,
                        )
                    } else {
                        Text(text = "Login")
                    }

                }

                if (loginError) {
                    Text(text = "Username or password is incorrect", color = Color.Red)
                }
            }
            Text(
                text = DirectToRegisterText(navController = navController),
            )

        }
    }


}