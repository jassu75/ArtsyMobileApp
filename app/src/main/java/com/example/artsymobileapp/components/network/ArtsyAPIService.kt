package com.example.artsymobileapp.components.network

import android.content.Context
import com.example.artsymobileapp.components.network.types.CheckAuthType.CheckAuthJson
import com.example.artsymobileapp.components.network.types.FavoritesType.FavoritesType
import com.example.artsymobileapp.components.network.types.UpdateFavorites.AddFavoritesType
import com.example.artsymobileapp.components.network.types.UpdateFavorites.DeleteFavoritesType
import com.example.artsymobileapp.components.network.types.userType.UserEmail
import com.example.artsymobileapp.components.network.types.artistDetailsType.ArtistDetailsJson
import com.example.artsymobileapp.components.network.types.artistlisttype.ArtistListJson
import com.example.artsymobileapp.components.network.types.categoryType.CategoryJson
import com.example.artsymobileapp.components.network.types.userType.loginUserType
import com.example.artsymobileapp.components.network.types.userType.registerUserType
import com.example.artsymobileapp.components.network.types.userType.userJson
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

private const val BASE_URL =
    "https://tejas-artsymobileapp.wl.r.appspot.com"

interface ArtsyApiService {
    @GET("/api/search/{artistName}")
    suspend fun getArtistList(@Path("artistName") artistName: String): ArtistListJson

    @GET("/api/artistdetails/{artistId}")
    suspend fun getArtistDetails(@Path("artistId") artistId: String): ArtistDetailsJson

    @GET("/api/category/{artworkId}")
    suspend fun getCategory(@Path("artworkId") artworkId: String): CategoryJson

    @POST("/api/db/login")
    suspend fun loginUser(@Body loginUserData: loginUserType): userJson

    @POST("/api/db/register")
    suspend fun registerUser(@Body registerUserData: registerUserType): userJson

    @POST("/api/deleteaccount")
    suspend fun deleteAccount(@Body email: UserEmail): String

    @POST("/api/logout")
    suspend fun logout(): String

    @GET("/api/retrievefavoritelist/{email}")
    suspend fun retrieveFavorites(@Path("email") email: String): FavoritesType

    @POST("/api/adduserfavorite/{artistId}")
    suspend fun addFavorite(@Path("artistId") artistId: String): AddFavoritesType

    @POST("/api/deleteuserfavorite/{artistId}")
    suspend fun deleteFavorite(@Path("artistId") artistId: String): DeleteFavoritesType

    @GET("/api/checkAuth")
    suspend fun checkAuth(): CheckAuthJson

}

object ArtsyAPI {
    lateinit var retrofitService: ArtsyApiService
        private set

    fun init(context: Context) {
        val okHttp = OkHttpClient.Builder()
            .cookieJar(ArtsyCookieJar.get(context)).build()


        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttp)
            .build()

        retrofitService =
            retrofit.create(ArtsyApiService::class.java)
    }


}