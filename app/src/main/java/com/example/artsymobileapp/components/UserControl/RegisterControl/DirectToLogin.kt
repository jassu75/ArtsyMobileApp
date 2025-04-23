package com.example.artsymobileapp.components.UserControl.RegisterControl


import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.navigation.NavController
import com.example.artsymobileapp.components.screens.screens


fun DirectToLoginText(navController: NavController): AnnotatedString {

    val originalText = "Already have an account? Login"
    val startIndex = originalText.indexOf("Login")
    val endIndex = originalText.length

    val normal_text = SpanStyle(color = Color.Blue)

    val DirectToLoginText = buildAnnotatedString {
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
                    navController.navigate(route = screens.Login.name)
                }),
            start = startIndex,
            end = endIndex
        )

    }

    return DirectToLoginText

}