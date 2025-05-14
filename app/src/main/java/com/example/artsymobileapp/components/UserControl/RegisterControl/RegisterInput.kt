package com.example.artsymobileapp.components.UserControl.RegisterControl

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.artsymobileapp.components.network.ViewModel.ArtsyViewModel
import com.example.artsymobileapp.components.network.types.userType.registerUserType

private val register_container = Modifier
    .fillMaxSize()
    .padding(20.dp)
private val register_items = Modifier.fillMaxWidth()
private val register_items_container = Modifier.widthIn(max = 600.dp)


@Composable
fun RegisterInput(navController: NavController, viewModel: ArtsyViewModel) {

    var fullname by rememberSaveable { mutableStateOf("") }
    var fullnameError by rememberSaveable { mutableStateOf(false) }
    var fullnameFirstFocus by rememberSaveable { mutableStateOf(false) }
    val fullnameErrorText by rememberSaveable { mutableStateOf("Fullname cannot be empty") }

    var email by rememberSaveable { mutableStateOf("") }
    var emailError by rememberSaveable { mutableStateOf(false) }
    var emailFirstFocus by rememberSaveable { mutableStateOf(false) }
    var emailErrorText by rememberSaveable { mutableStateOf("Email cannot be empty") }

    var password by rememberSaveable { mutableStateOf("") }
    var passwordError by rememberSaveable { mutableStateOf(false) }
    var passwordFirstFocus by rememberSaveable { mutableStateOf(false) }
    val passwordErrorText by rememberSaveable { mutableStateOf("Password cannot be empty") }

    var registerError by rememberSaveable { mutableStateOf(false) }

    val passwordTransformation = VisualTransformation {
        TransformedText(
            AnnotatedString("*".repeat(it.text.length)), OffsetMapping.Identity
        )
    }

    var loading by rememberSaveable { mutableStateOf(false) }

    Box(modifier = register_container, contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(modifier = register_items_container) {
                OutlinedTextField(
                    value = fullname,
                    isError = fullnameError,
                    onValueChange = {
                        fullname = it
                        if (fullname.isEmpty()) {
                            fullnameError = true
                        } else {
                            fullnameError = false
                        }
                    },
                    label = { Text(text = "Enter fullname") },
                    placeholder = { Text(text = "Enter fullname") },
                    modifier = register_items.onFocusChanged { focusState ->
                        if (!fullnameFirstFocus && focusState.isFocused) {
                            fullnameFirstFocus = true
                        }


                    },
                )
                if (fullnameFirstFocus && fullnameError) {
                    Text(text = fullnameErrorText, color = Color.Red)
                }
            }

            Column(modifier = register_items_container) {
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
                        if (registerError) {
                            registerError = false
                        }
                    },
                    label = { Text(text = "Enter email") },
                    placeholder = { Text(text = "Enter email") },
                    isError = emailError,
                    modifier = register_items.onFocusChanged { focusState ->
                        if (!emailFirstFocus && focusState.isFocused) {
                            emailFirstFocus = true
                        }

                    },
                )
                if (emailFirstFocus && emailError) {
                    Text(text = emailErrorText, color = Color.Red)
                } else if (registerError) {
                    Text(text = "Email already exists", color = Color.Red)
                }
            }
            Column(modifier = register_items_container) {
                OutlinedTextField(
                    value = password,
                    isError = passwordError,
                    onValueChange = {
                        password = it
                        if (password.isEmpty()) {
                            passwordError = true
                        } else {
                            passwordError = false
                        }
                    },
                    label = { Text(text = "Enter password") },
                    visualTransformation = passwordTransformation,
                    placeholder = { Text(text = "Enter password") },
                    modifier = register_items.onFocusChanged { focusState ->
                        if (!passwordFirstFocus && focusState.isFocused) {
                            passwordFirstFocus = true
                        }


                    },
                )
                if (passwordFirstFocus && passwordError) {
                    Text(text = passwordErrorText, color = Color.Red)
                }
            }

            Column(modifier = register_items_container) {
                Button(
                    modifier = register_items,
                    enabled = !loading,
                    onClick = {
                        val emailRegex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$")

                        if (!emailRegex.matches(email)) {
                            emailErrorText = "Invalid email format"
                            emailError = true
                        } else {
                            val userRegisterData = registerUserType(
                                fullname = fullname,
                                email = email,
                                password = password
                            )
                            viewModel.registerUser(
                                setLoading = { loading = it },
                                userRegisterData = userRegisterData,
                                navController = navController,
                                setRegisterError = { registerError = it }
                            )

                        }
                    }
                ) {
                    if (loading) {
                        CircularProgressIndicator(
                            color = Color.White,
                        )
                    } else {
                        Text(text = "Register")
                    }

                }
            }



            DirectToLoginText(navController = navController)

        }
    }


}