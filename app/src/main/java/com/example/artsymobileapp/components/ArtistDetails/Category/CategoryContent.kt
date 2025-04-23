package com.example.artsymobileapp.components.ArtistDetails.Category

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.artsymobileapp.components.network.types.categoryType.CategoryType
import kotlinx.coroutines.launch

val content_container = Modifier
    .fillMaxWidth()
    .padding(vertical = 12.dp)

val carousal_container = Modifier.width(240.dp)

@Composable
fun CategoryContent(categoryList: List<CategoryType>) {

    val carousalState = rememberPagerState(pageCount = { Int.MAX_VALUE })
    val coroutineScope = rememberCoroutineScope()

    Row(modifier = content_container, verticalAlignment = Alignment.CenterVertically) {

        IconButton(onClick = {
            if (carousalState.settledPage > 0) {
                coroutineScope.launch {
                    carousalState.animateScrollToPage((carousalState.settledPage - 1) % categoryList.size)
                }
            }
        }) {
            Icon(
                Icons.Default.KeyboardArrowLeft, contentDescription = "Left Arrow"
            )
        }


        HorizontalPager(
            state = carousalState,
            pageSpacing = 12.dp,
            modifier = carousal_container
        ) { index ->
            categoryList.getOrNull(index % categoryList.size)?.let { category ->
                CategoryCard(
                    id = category.id,
                    title = category.title,
                    image = category.image,
                    description = category.description
                )
            }

        }

        IconButton(onClick = {
            coroutineScope.launch {
                carousalState.animateScrollToPage((carousalState.settledPage + 1) % categoryList.size)
            }

        }) {
            Icon(
                Icons.Default.KeyboardArrowRight, contentDescription = "Right Arrow"
            )
        }
    }

}