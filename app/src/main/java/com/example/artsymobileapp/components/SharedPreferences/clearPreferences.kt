package com.example.artsymobileapp.components.SharedPreferences

import android.content.Context
import androidx.core.content.edit
import com.example.artsymobileapp.components.network.ArtsyCookieJar

fun clearPreferences(context: Context) {
    val artsyCookieJar=    ArtsyCookieJar()
    val sharedPreference = context.getSharedPreferences("User", Context.MODE_PRIVATE)
    sharedPreference.edit() { clear() }
    artsyCookieJar.clearCookieJar()

}