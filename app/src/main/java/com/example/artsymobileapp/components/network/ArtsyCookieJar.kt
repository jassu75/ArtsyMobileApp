package com.example.artsymobileapp.components.network

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

class ArtsyCookieJar : CookieJar {

    private val artsyCookieJar: MutableMap<String, List<Cookie>> = mutableMapOf()

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        val existingCookies = artsyCookieJar[url.host]?.toMutableList() ?: mutableListOf()
        existingCookies.removeAll { newCookie -> cookies.any { it.name == newCookie.name } }
        existingCookies.addAll(cookies)
        artsyCookieJar[url.host] = existingCookies

    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        return artsyCookieJar[url.host] ?: emptyList()
    }

}