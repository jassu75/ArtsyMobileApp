

package com.example.artsymobileapp.components.network

import com.example.artsymobileapp.components.network.types.artistDetailsType.ArtistDetailsJson
import com.example.artsymobileapp.components.network.types.artistlisttype.ArtistListJson
import com.example.artsymobileapp.components.network.types.categoryType.CategoryJson
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL =
    "https://tejas-artsymobileapp.wl.r.appspot.com"


private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface ArtsyApiService {
    @GET("/api/search/{artistName}")
    suspend fun getArtistList(@Path("artistName") artistName: String): ArtistListJson

    @GET("/api/artistdetails/{artistId}")
    suspend fun getArtistDetails(@Path("artistId") artistId:String): ArtistDetailsJson

    @GET("/api/category/{artworkId}")
    suspend fun getCategory(@Path("artworkId") artworkId:String): CategoryJson
}

object ArtsyAPI {
    val retrofitService: ArtsyApiService by lazy {
        retrofit.create(ArtsyApiService::class.java)
    }
}