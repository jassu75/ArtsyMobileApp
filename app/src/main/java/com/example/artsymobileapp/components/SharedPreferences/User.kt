package com.example.artsymobileapp.components.SharedPreferences

import android.content.Context
import androidx.core.content.edit
import com.example.artsymobileapp.components.network.types.userType.UserType
import com.google.gson.Gson

fun writeUser(context: Context, value: UserType?) {
    val sharedPreferences = context.getSharedPreferences("User", Context.MODE_PRIVATE)
    sharedPreferences.edit() {
        val userJson = Gson().toJson(value)
        putString("user", userJson)
    }
}

fun readUser(context: Context): UserType? {
    val sharedPreferences = context.getSharedPreferences("User", Context.MODE_PRIVATE)
    val userJson = sharedPreferences.getString("user", null)
    if (userJson != null && userJson != "null") {
        return Gson().fromJson(userJson, UserType::class.java)
    } else {
        return null
    }
}