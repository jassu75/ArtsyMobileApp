package com.example.artsymobileapp.components.UserControl.LoginControl

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.navigation.NavController
import com.example.artsymobileapp.components.screens.screens


fun DirectToRegisterText(navController: NavController): AnnotatedString {

    val originalText = "Don't have an account yet? Register"
    val startIndex = originalText.indexOf("Register")
    val endIndex = originalText.length

    val normal_text = SpanStyle(color = Color.Blue)

    val DirectToRegisterText = buildAnnotatedString {
        append(originalText)

        addStyle(
            style = normal_text,
            start = startIndex,
            end = endIndex
        )

        addLink(
            LinkAnnotation.Clickable(
                "ClickableLinkTag",
                linkInteractionListener = {
                    navController.navigate(route = screens.Register.name)
                }),
            start = startIndex,
            end = endIndex
        )

    }

    return DirectToRegisterText

}