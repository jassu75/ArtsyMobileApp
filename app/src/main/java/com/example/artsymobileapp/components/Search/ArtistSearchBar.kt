@file:OptIn(ExperimentalMaterial3Api::class)

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.TextFieldValue


val searchbar = Modifier.semantics { isTraversalGroup = true }

fun fetchArtists(searchText: String) {
    println(searchText)
}

@Composable
fun ArtistSearchBar(isSearching: Boolean) {
    var searchText by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
    }


    SearchBar(
        modifier = searchbar,
        inputField = {
            SearchBarDefaults.InputField(
                query = searchText.text,
                onQueryChange = { userText ->
                    searchText = TextFieldValue(userText)
                    fetchArtists(userText)
                },
                expanded = isSearching,
                onExpandedChange = { searchText = TextFieldValue("") },
                onSearch = { searchText = TextFieldValue("") },
            )
        },
        expanded = isSearching,
        onExpandedChange = { searchText = TextFieldValue("") },
    ) {

    }

}