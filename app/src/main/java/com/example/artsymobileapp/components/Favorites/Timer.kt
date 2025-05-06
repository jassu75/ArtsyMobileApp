package com.example.artsymobileapp.components.Favorites

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import java.util.Date

@Composable
fun Timer(createdAt: Date) {

    var present by rememberSaveable { mutableStateOf(Date()) }
    LaunchedEffect(Unit) {
        while (true) {
            present = Date()
            delay(1000)
        }
    }

    val timerText = handleInterval(createdAt = createdAt, present = present)
    Text(text = timerText)
}

fun handleInterval(createdAt: Date, present: Date): String {

    val intervalSec = ((present.time - createdAt.time) / 1000).toInt()

    return when {
        intervalSec < 60 -> {
            "$intervalSec ${if (intervalSec == 1) "second" else "seconds"} ago"
        }

        intervalSec < 3600 -> {
            val intervalMin = intervalSec / 60
            "$intervalMin ${if (intervalMin == 1) "minute" else "minutes"} ago"
        }

        intervalSec < 86400 -> {
            val intervalHours = intervalSec / 3600
            "$intervalHours ${if (intervalHours == 1) "hour" else "hours"} ago"
        }

        intervalSec < 2592000 -> { // 30 days
            val intervalDays = intervalSec / 86400
            "$intervalDays ${if (intervalDays == 1) "day" else "days"} ago"
        }

        else -> {
            val intervalMonths = intervalSec / 2592000
            "$intervalMonths ${if (intervalMonths == 1) "month" else "months"} ago"
        }
    }
}