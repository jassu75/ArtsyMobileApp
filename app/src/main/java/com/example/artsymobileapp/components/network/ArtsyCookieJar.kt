package com.example.artsymobileapp.components.network

import android.content.Context
import com.franmontiel.persistentcookiejar.ClearableCookieJar
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor

object ArtsyCookieJar {

    private var cookieJar: ClearableCookieJar? = null

    fun get(context: Context): ClearableCookieJar {
        if (cookieJar == null) {
            cookieJar = PersistentCookieJar(
                SetCookieCache(),
                SharedPrefsCookiePersistor(context)
            )
        }
        return cookieJar as ClearableCookieJar
    }

    fun clearCookieJar(context: Context) {
        val cookieJar = get(context)
        cookieJar.clear()
    }
}