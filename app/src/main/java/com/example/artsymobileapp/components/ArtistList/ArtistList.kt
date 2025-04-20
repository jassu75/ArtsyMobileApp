import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.artsymobileapp.components.ArtistList.ArtistCard
import com.example.artsymobileapp.components.network.ViewModel.ArtistListUiState

val artistlist_container = Modifier
    .fillMaxWidth()
    .padding(horizontal = 20.dp, vertical = 20.dp)


@Composable
fun Artistlist(artistList: ArtistListUiState,navController: NavController) {
    if ( artistList is ArtistListUiState.Success ) {
        LazyColumn(
            modifier = artistlist_container,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(artistList.artistListDetails) { artistInfo ->
                Box(modifier = Modifier.fillMaxWidth())
                {
                    ArtistCard(
                        id = artistInfo.id,
                        title = artistInfo.title,
                        image = artistInfo.image,
                        navController=navController
                    )
                }
            }

        }


    }
}