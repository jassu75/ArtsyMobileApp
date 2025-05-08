package com.example.artsymobileapp.components.network.ViewModel

import ArtistListType
import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.artsymobileapp.components.network.ArtistDetailsLoadingState
import com.example.artsymobileapp.components.network.ArtistListLoadingState
import com.example.artsymobileapp.components.network.ArtsyAPI
import com.example.artsymobileapp.components.network.ArtsyCookieJar
import com.example.artsymobileapp.components.network.CategoryLoadingState
import com.example.artsymobileapp.components.network.FavoritesLoadingState
import com.example.artsymobileapp.components.network.types.userType.UserEmail
import com.example.artsymobileapp.components.network.types.artistDetailsType.ArtistDDetailsType
import com.example.artsymobileapp.components.network.types.artistDetailsType.ArtistInfoType
import com.example.artsymobileapp.components.network.types.artistDetailsType.ArtworksType
import com.example.artsymobileapp.components.network.types.artistDetailsType.SimilarArtistListType
import com.example.artsymobileapp.components.network.types.categoryType.CategoryType
import com.example.artsymobileapp.components.network.types.userType.UserType
import com.example.artsymobileapp.components.network.types.userType.loginUserType
import com.example.artsymobileapp.components.network.types.userType.registerUserType
import com.example.artsymobileapp.components.screens.screens
import kotlinx.coroutines.launch
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow


class ArtsyViewModel : ViewModel() {

    private var searchJob: Job? = null

    var isInitialised by mutableStateOf(false)
        private set

    var artistListUiState: ArtistListLoadingState by mutableStateOf(ArtistListLoadingState.Loading)
        private set

    var artistDetailsUIState: ArtistDetailsLoadingState by mutableStateOf(ArtistDetailsLoadingState.Loading)
        private set

    var categoryUIState: CategoryLoadingState by mutableStateOf(CategoryLoadingState.Loading)
        private set

    var favoriteIdsList = mutableStateListOf<String>()
        private set

    var favoritesListUIState: FavoritesLoadingState by mutableStateOf(FavoritesLoadingState.Loading)
        private set

    var authenticated = mutableStateOf(false)
        private set

    var user = mutableStateOf<UserType?>(null)
        private set

    private val snackBar = MutableSharedFlow<String>()
    val snackBarMessage = snackBar.asSharedFlow()

    var searchText = mutableStateOf("")
        private set

    var isSearching= mutableStateOf(false)
        private set

    init {
        checkAuth()
    }

    fun setSearchText(text: String) {
        searchText.value = text
    }

    fun setisSearching(value:Boolean)
    {
        isSearching.value=value
    }

    fun clearArtistList()
    {
        artistListUiState=ArtistListLoadingState.Success(emptyList())
    }

    private fun setAuthenticated(value: Boolean) {
        authenticated.value = value
    }

    private fun setUser(value: UserType?) {
        user.value = value
    }

    fun showSnackBar(text: String) {
        viewModelScope.launch {
            snackBar.emit(text)
        }
    }


    fun getArtistList() {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
                delay(400)
                try {

                    val artistList = ArtsyAPI.retrofitService.getArtistList(searchText.value)
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
        navController: NavController,
        setLoginError: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            try {

                setLoading(true)
                val user = ArtsyAPI.retrofitService.loginUser(userLoginData)

                val favoriteIdsList = user.favoritesList.map { favorite ->
                    favorite.artistId
                }

                setFavoriteIdsList(favoriteIdsList)

                setAuthenticated(value = true)
                setUser(value = user.user)
                showSnackBar("Logged in successfully")
                navController.navigate(route = screens.Homepage.name)
            } catch (e: Exception) {
                Log.e("Login Error", "$e")
                setLoginError(true)
                setAuthenticated(value = false)
                setUser(value = null)

            } finally {
                setLoading(false)
            }
        }

    }

    fun registerUser(
        userRegisterData: registerUserType,
        setLoading: (Boolean) -> Unit,
        navController: NavController,
        setRegisterError: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            try {

                setLoading(true)
                val user = ArtsyAPI.retrofitService.registerUser(userRegisterData)

                val favoriteIdsList = user.favoritesList.map { favorite ->
                    favorite.artistId
                }

                setFavoriteIdsList(favoriteIdsList)
                setAuthenticated(value = true)
                setUser(value = user.user)
                showSnackBar("Registered successfully")
                navController.navigate(route = screens.Homepage.name)
            } catch (e: Exception) {
                Log.e("Register Error", "$e")
                setRegisterError(true)
                setAuthenticated(value = false)
                setUser(value = null)
            } finally {
                setLoading(false)
            }
        }

    }

    fun logout(context: Context, navController: NavController) {
        viewModelScope.launch {
            try {
                ArtsyAPI.retrofitService.logout()
                ArtsyCookieJar.clearCookieJar(context = context)
                setUser(value = null)
                setAuthenticated(value = false)
                showSnackBar("Logged out successfully")

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
                ArtsyCookieJar.clearCookieJar(context = context)
                setUser(value = null)
                setAuthenticated(value = false)
                showSnackBar("Deleted user successfully")
                navController.navigate(route = screens.Homepage.name)
            } catch (e: Exception) {
                Log.e("Delete account Error", "$e")
            }

        }
    }

    fun addFavoriteId(artistId: String) {
        viewModelScope.launch {
            try {
                if (artistId !in favoriteIdsList) {
                    favoriteIdsList.add(artistId)
                    val addFavoriteJson = ArtsyAPI.retrofitService.addFavorite(artistId)
                    if (addFavoriteJson.favoritesList != null) {
                        favoritesListUIState =
                            FavoritesLoadingState.Success(addFavoriteJson.favoritesList)
                        showSnackBar("Added to Favorites")

                    }
                }
            } catch (e: Exception) {
                Log.e("---Failed add favorite", "$e")
            }
        }
    }

    fun removeFavoriteId(artistId: String) {
        viewModelScope.launch {
            try {
                if (artistId in favoriteIdsList) {
                    favoriteIdsList.remove(artistId)

                }
                val deleteFavoriteJson = ArtsyAPI.retrofitService.deleteFavorite(artistId)
                if (deleteFavoriteJson.favoritesList != null) {
                    favoritesListUIState =
                        FavoritesLoadingState.Success(deleteFavoriteJson.favoritesList)
                    showSnackBar("Removed from favorites")

                }
            } catch (e: Exception) {
                Log.e("Failed delete favorite", "$e")

            }
        }


    }

    fun setFavoriteIdsList(favoriteIds: List<String>) {
        favoriteIdsList.clear()
        favoriteIdsList.addAll(favoriteIds)
    }

    fun retrieveFavorites(email: String) {
        viewModelScope.launch {
            try {
                val favoritesJson = ArtsyAPI.retrofitService.retrieveFavorites(email)
                favoritesListUIState = FavoritesLoadingState.Success(favoritesJson)
                val favoriteIdList = favoritesJson.map { favorite -> favorite.artistId }
                setFavoriteIdsList(favoriteIdList)


            } catch (e: Exception) {
                Log.e("---Fail Favorite fetch", "$e")
                favoritesListUIState = FavoritesLoadingState.Error
            }
        }
    }

    private fun checkAuth() {
        viewModelScope.launch {
            try {
                val checkAuthJson = ArtsyAPI.retrofitService.checkAuth()
                setAuthenticated(value = true)
                setUser(value = checkAuthJson.user)
                if (user.value != null) {
                    retrieveFavorites(user.value!!.email)
                }

            } catch (e: Exception) {
                Log.e("Auth failed", "$e")
                setAuthenticated(value = false)
                setUser(value = null)

            } finally {
                isInitialised = true

            }
        }

    }

}