import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.artsymobileapp.components.network.ViewModel.ArtistListUiState

@Composable
fun Artistlist(artistList: ArtistListUiState) {
    if (artistList is ArtistListUiState.Success) {
        val artists = artistList.artists
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = artists.joinToString("\n\n") { artist ->
                    "Name: ${artist.title}\nID: ${artist.id}\nImage: ${artist.image}"
                }
            )
        }
        Log.i("ArtistList", "$artists")
    }
}