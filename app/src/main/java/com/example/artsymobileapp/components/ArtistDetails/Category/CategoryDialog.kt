package com.example.artsymobileapp.components.ArtistDetails.Category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.artsymobileapp.components.ArtistDetails.Loading
import com.example.artsymobileapp.components.network.CategoryLoadingState
import com.example.artsymobileapp.components.network.ViewModel.ArtsyViewModel

val dialog_container = Modifier
    .fillMaxWidth()

val dialog_content = Modifier
    .fillMaxWidth()
    .padding(vertical = 24.dp)


val cateogry_text = TextStyle(fontSize = 25.sp)

val category_text_container = Modifier.padding(horizontal = 18.dp)

val button_container = Modifier
    .fillMaxWidth()
    .padding(horizontal = 18.dp)

@Composable
fun CategoryDialog(
    viewModel: ArtsyViewModel,
    categoryDwtails: CategoryLoadingState,
    handleClose: () -> Unit,
    artworkId: String
) {
    LaunchedEffect(artworkId) {
        viewModel.getCategory(artworkId)
    }
    Dialog(onDismissRequest = handleClose) {
        Card(modifier = dialog_container) {
            Column(modifier = dialog_content, verticalArrangement = Arrangement.spacedBy(18.dp)) {
                Row(modifier = category_text_container)
                {
                    Text(text = "Categories", style = cateogry_text)
                }
                if (categoryDwtails is CategoryLoadingState.Loading) {
                    Loading()
                } else if (categoryDwtails is CategoryLoadingState.Success) {
                    if (categoryDwtails.category.isEmpty()) {
                        EmptyCategories()
                    } else {
                        CategoryContent(categoryList = categoryDwtails.category)
                    }
                }

                Row(modifier = button_container, horizontalArrangement = Arrangement.End) {
                    Button(onClick = handleClose) { Text(text = "Close") }
                }

            }
        }

    }

}