@file:OptIn(ExperimentalMaterial3Api::class)

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.artsymobileapp.components.network.ViewModel.ArtsyViewModel

private val searchbar = Modifier.semantics { isTraversalGroup = true }
private val search_icon = Modifier.padding(top = 8.dp)
private val close_icon = Modifier.padding(top = 8.dp)

fun fetchArtists(searchText: String, viewModel: ArtsyViewModel) {
    viewModel.getArtistList(searchText)
}

@Composable
fun ArtistSearchBar(
    viewModel: ArtsyViewModel,
    isSearching: Boolean,
    setIsSearching: (Boolean) -> Unit,
    navController: NavController
) {
    var searchText by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .semantics { isTraversalGroup = true }
    ) {
        SearchBar(
            modifier = searchbar,
            inputField = {
                SearchBarDefaults.InputField(
                    query = searchText.text,
                    onQueryChange = { userText ->
                        searchText = TextFieldValue(userText)
                        fetchArtists(searchText = userText, viewModel = viewModel)
                    },
                    expanded = isSearching,
                    onExpandedChange = {
                        searchText = TextFieldValue("")
                        fetchArtists(searchText = "", viewModel = viewModel)
                    },
                    onSearch = { },
                    leadingIcon = {
                        Box(modifier = search_icon) {
                            Icon(
                                Icons.Default.Search, contentDescription = "Search"
                            )
                        }
                    },
                    trailingIcon = {
                        Box(modifier = close_icon) {
                            IconButton(onClick = {
                                setIsSearching(false)
                            }) {
                                Icon(
                                    Icons.Default.Clear, contentDescription = "Close"
                                )
                            }
                        }
                    }
                )
            },
            expanded = isSearching,
            onExpandedChange = {
                searchText = TextFieldValue("")
                fetchArtists(searchText = "", viewModel = viewModel)
            },
            colors = SearchBarDefaults.colors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
            )

        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.secondaryContainer)
            ) {
                Artistlist(
                    artistList = viewModel.artistListUiState,
                    navController = navController,
                    viewModel = viewModel
                )
            }

        }
    }
}