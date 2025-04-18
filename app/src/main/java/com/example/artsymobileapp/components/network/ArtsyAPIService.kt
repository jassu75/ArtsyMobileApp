

package com.example.artsymobileapp.components.network

import com.example.artsymobileapp.components.network.types.artistlisttype.ArtistListJson
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL =
    "https://tejas-artify-app.uw.r.appspot.com"


private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface ArtsyApiService {
    @GET("/api/search/{artistName}")
    suspend fun getArtistList(@Path("artistName") artistName: String): ArtistListJson
}

object ArtsyAPI {
    val retrofitService: ArtsyApiService by lazy {
        retrofit.create(ArtsyApiService::class.java)
    }
}