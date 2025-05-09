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
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.artsymobileapp.components.network.ViewModel.ArtsyViewModel

private val searchbar = Modifier.semantics { isTraversalGroup = true }
private val search_icon = Modifier.padding(top = 8.dp)
private val close_icon = Modifier.padding(top = 8.dp)
private val placeholder_text = TextStyle(fontSize = 20.sp)


@Composable
fun ArtistSearchBar(
    viewModel: ArtsyViewModel,
    isSearching: MutableState<Boolean>,
    navController: NavController,
    searchText: MutableState<String>
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .semantics { isTraversalGroup = true }
    ) {
        SearchBar(
            modifier = searchbar,
            inputField = {
                SearchBarDefaults.InputField(
                    query = searchText.value,
                    onQueryChange = { userText ->
                        viewModel.setSearchText(text = userText)
                        if (searchText.value.length > 3) {
                            viewModel.getArtistList()
                        } else {
                            viewModel.clearArtistList()
                        }
                    },
                    expanded = isSearching.value,
                    onExpandedChange = {
                        viewModel.setisSearching(it)
                    },
                    placeholder = {
                        Text(
                            text = "Search artists...",
                            style = placeholder_text,
                            textAlign = TextAlign.End
                        )
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
                                viewModel.setisSearching(false)
                                viewModel.clearArtistList()
                                viewModel.setSearchText(text = "")
                            }) {
                                Icon(
                                    Icons.Default.Clear, contentDescription = "Close"
                                )
                            }
                        }
                    }
                )
            },
            expanded = isSearching.value,
            onExpandedChange = {
                viewModel.setisSearching(it)

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