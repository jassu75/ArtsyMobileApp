package com.example.artsymobileapp.components.SharedPreferences

import android.content.Context
import androidx.core.content.edit

fun writeAuthenticated(context: Context, value: Boolean) {
    val sharedPreferences = context.getSharedPreferences("User", Context.MODE_PRIVATE)
    sharedPreferences.edit() {
        putBoolean("authenticated", value)
    }
}

fun readAuthenticated(context: Context):Boolean{
    val sharedPreferences=context.getSharedPreferences("User",Context.MODE_PRIVATE)
    return sharedPreferences.getBoolean("authenticated",false)
}