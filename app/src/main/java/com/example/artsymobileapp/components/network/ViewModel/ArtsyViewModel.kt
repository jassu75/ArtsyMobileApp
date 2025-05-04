package com.example.artsymobileapp.components.network.ViewModel

import ArtistListType
import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.artsymobileapp.components.SharedPreferences.clearPreferences
import com.example.artsymobileapp.components.SharedPreferences.writeAuthenticated
import com.example.artsymobileapp.components.SharedPreferences.writeUser
import com.example.artsymobileapp.components.network.ArtistDetailsLoadingState
import com.example.artsymobileapp.components.network.ArtistListLoadingState
import com.example.artsymobileapp.components.network.ArtsyAPI
import com.example.artsymobileapp.components.network.CategoryLoadingState
import com.example.artsymobileapp.components.network.types.UserEmail
import com.example.artsymobileapp.components.network.types.artistDetailsType.ArtistDDetailsType
import com.example.artsymobileapp.components.network.types.artistDetailsType.ArtistInfoType
import com.example.artsymobileapp.components.network.types.artistDetailsType.ArtworksType
import com.example.artsymobileapp.components.network.types.artistDetailsType.SimilarArtistListType
import com.example.artsymobileapp.components.network.types.categoryType.CategoryType
import com.example.artsymobileapp.components.network.types.userType.loginUserType
import com.example.artsymobileapp.components.network.types.userType.registerUserType
import com.example.artsymobileapp.components.screens.screens
import kotlinx.coroutines.launch
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay


class ArtsyViewModel : ViewModel() {

    private var searchJob: Job? = null

    var artistListUiState: ArtistListLoadingState by mutableStateOf(ArtistListLoadingState.Loading)
        private set

    var artistDetailsUIState: ArtistDetailsLoadingState by mutableStateOf(ArtistDetailsLoadingState.Loading)
        private set

    var categoryUIState: CategoryLoadingState by mutableStateOf(CategoryLoadingState.Loading)
        private set


    fun getArtistList(artistName: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            if (artistName.length > 3) {
                delay(400)
                try {

                    artistListUiState = ArtistListLoadingState.Loading
                    val artistList = ArtsyAPI.retrofitService.getArtistList(artistName)
                    val refinedList = artistList.map { artistInfo ->

                        val image =
                            if (artistInfo._links.thumbnail.href == "/assets/shared/missing_image.png")
                                "/assets/artsy_logo.svg"
                            else
                                artistInfo._links.thumbnail.href

                        ArtistListType(
                            id = artistInfo._links.self.href.split("/").last(),
                            title = artistInfo.title,
                            image = image
                        )
                    }
                    artistListUiState = ArtistListLoadingState.Success(refinedList)

                } catch (e: Exception) {
                    Log.e("FETCHARTISTLIST", "$e")
                    artistListUiState = ArtistListLoadingState.Error
                }
            } else {
                artistListUiState = ArtistListLoadingState.Success(emptyList())
            }
        }
    }

    fun getArtistDetails(artistId: String) {
        viewModelScope.launch {
            try {

                artistDetailsUIState = ArtistDetailsLoadingState.Loading
                val artistDetails = ArtsyAPI.retrofitService.getArtistDetails(artistId)

                val refinedArtistInfo = ArtistInfoType(
                    artistName = artistDetails.artistInfo.name,
                    birthDay = artistDetails.artistInfo.birthday,
                    deathDay = artistDetails.artistInfo.deathday,
                    nationality = artistDetails.artistInfo.nationality,
                    biography = artistDetails.artistInfo.biography
                )

                val refinedArtworks = artistDetails.artWorks._embedded.artworks.map { artworkInfo ->
                    ArtworksType(
                        id = artworkInfo.id,
                        title = artworkInfo.title,
                        date = artworkInfo.date,
                        image = artworkInfo._links.thumbnail.href
                    )
                }

                val refinedSimilarArtists = artistDetails.similarArtistList.map { similarArtist ->
                    SimilarArtistListType(
                        id = similarArtist.id,
                        title = similarArtist.name,
                        image = similarArtist._links.thumbnail.href
                    )
                }

                val refinedArtistDetails = ArtistDDetailsType(
                    artistInfo = refinedArtistInfo,
                    artWorks = refinedArtworks,
                    similarArtists = refinedSimilarArtists
                )

                artistDetailsUIState = ArtistDetailsLoadingState.Success(refinedArtistDetails)


            } catch (e: Exception) {
                Log.e("FETCH ARTIST DETAILS", "$e")
                artistListUiState = ArtistListLoadingState.Error
            }

        }
    }

    fun getCategory(artworkId: String) {
        viewModelScope.launch {
            try {

                categoryUIState = CategoryLoadingState.Loading
                val categoryList = ArtsyAPI.retrofitService.getCategory(artworkId)

                val refinedCategory = categoryList._embedded.genes.map { category ->
                    CategoryType(
                        id = category.id,
                        title = category.name,
                        image = category._links.thumbnail.href,
                        description = category.description
                    )
                }



                categoryUIState = CategoryLoadingState.Success(refinedCategory)


            } catch (e: Exception) {
                Log.e("FETCH CATEGORY", "$e")
                categoryUIState = CategoryLoadingState.Error
            }

        }
    }

    fun loginUser(
        userLoginData: loginUserType,
        setLoading: (Boolean) -> Unit,
        context: Context,
        navController: NavController,
        setLoginError: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            try {

                setLoading(true)
                val user = ArtsyAPI.retrofitService.loginUser(userLoginData)
                writeAuthenticated(context = context, value = true)
                writeUser(context = context, value = user.user)
                navController.navigate(route = screens.Homepage.name)
            } catch (e: Exception) {
                Log.e("Login Error", "$e")
                setLoginError(true)
                writeAuthenticated(context = context, value = false)
                writeUser(context = context, value = null)
            } finally {
                setLoading(false)
            }
        }

    }

    fun registerUser(
        userRegisterData: registerUserType,
        setLoading: (Boolean) -> Unit,
        context: Context,
        navController: NavController,
        setRegisterError: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            try {

                setLoading(true)
                val user = ArtsyAPI.retrofitService.registerUser(userRegisterData)
                writeAuthenticated(context = context, value = true)
                writeUser(context = context, value = user.user)
                navController.navigate(route = screens.Homepage.name)
            } catch (e: Exception) {
                Log.e("Register Error", "$e")
                setRegisterError(true)
                writeAuthenticated(context = context, value = false)
                writeUser(context = context, value = null)
            } finally {
                setLoading(false)
            }
        }

    }

    fun logout(context: Context, navController: NavController) {
        viewModelScope.launch {
            try {
                ArtsyAPI.retrofitService.logout()
                clearPreferences(context = context)
                navController.navigate(route = screens.Homepage.name)
            } catch (e: Exception) {
                Log.e("Logout Error", "$e")
            }
        }
    }

    fun deleteAccount(context: Context, navController: NavController, email: String) {
        viewModelScope.launch {
            try {
                val userEmail = UserEmail(email = email)
                ArtsyAPI.retrofitService.deleteAccount(email = userEmail)
                clearPreferences(context = context)
                navController.navigate(route = screens.Homepage.name)
            } catch (e: Exception) {
                Log.e("Delete account Error", "$e")
            }

        }
    }

}