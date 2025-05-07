package com.example.artsymobileapp.components.UserControl.RegisterControl


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.navigation.NavController
import com.example.artsymobileapp.components.screens.screens

@Composable
fun DirectToLoginText(navController: NavController) {

    val originalText = "Already have an account? Login"
    val startIndex = originalText.indexOf("Login")
    val endIndex = originalText.length

    val linkColor = if (isSystemInDarkTheme()) Color(0xFF5F9EA0) else Color(0xFF0000FF)


    val DirectToLoginText = buildAnnotatedString {
        append(originalText)

        addStyle(
            style = SpanStyle(color = linkColor),
            start = startIndex,
            end = endIndex
        )

        addLink(
            LinkAnnotation.Clickable(
                "ClickableLinkTag",
                linkInteractionListener = {
                    navController.navigate(route = screens.Login.name)
                }),
            start = startIndex,
            end = endIndex
        )

    }

    Text(text = DirectToLoginText)

}