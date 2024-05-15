package com.danchez.mercadolibrechallenge.presentation.product_details.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.danchez.mercadolibrechallenge.R
import com.danchez.mercadolibrechallenge.presentation.theme.extraSmall
import com.danchez.mercadolibrechallenge.presentation.theme.small

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductImagesComposable(pictures: List<String>) {

    val pageState = rememberPagerState(pageCount = { pictures.size })

    Column {
        HorizontalPager(state = pageState) { position ->
            SubcomposeAsyncImage(
                model = pictures[position],
                contentDescription = stringResource(id = R.string.product_image_content_description),
                modifier = Modifier.fillMaxWidth(),
                loading = {
                    CircularProgressIndicator()
                }
            )
        }
        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(bottom = extraSmall),
            horizontalArrangement = Arrangement.Center,
        ) {
            repeat(pageState.pageCount) { iteration ->
                val color = if (pageState.currentPage == iteration) Color.DarkGray else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(small)
                )
            }
        }
    }
}
