package com.danchez.mercadolibrechallenge.presentation.product_details.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danchez.mercadolibrechallenge.R
import com.danchez.mercadolibrechallenge.domain.model.ProductDetails
import com.danchez.mercadolibrechallenge.presentation.theme.medium
import com.danchez.mercadolibrechallenge.presentation.theme.small

@Composable
fun ProductDetailsComposable(
    details: ProductDetails,
) {
    BottomShadow()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(medium)
            .verticalScroll(rememberScrollState()),
    ) {
        Row(
            modifier = Modifier
                .padding(bottom = 2.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val productCondition = when (details.condition) {
                "new" -> stringResource(id = R.string.condition_new)
                "used" -> stringResource(id = R.string.condition_used)
                else -> null
            }
            productCondition?.let {
                Text(
                    text = it,
                    modifier = Modifier.align(Alignment.Bottom),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
        Spacer(modifier = Modifier.height(medium))
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = details.title,
            style = MaterialTheme.typography.titleLarge.copy(color = Color.Gray),
        )
        ProductImagesComposable(pictures = details.pictures)
        if (details.freeShipping) {
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = stringResource(id = R.string.free_shipping),
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Green),
            )
        }
        Spacer(modifier = Modifier.height(small))
        details.originalPrice?.let { originalPrice ->
            Text(
                text = originalPrice,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleLarge.copy(textDecoration = TextDecoration.LineThrough),
                color = Color.Gray,
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = details.price,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                color = Color.Black,
            )
            Spacer(modifier = Modifier.width(small))
            details.discount?.let { discount ->
                Text(
                    text = discount,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Green,
                )
            }
        }
    }
}

@Composable
private fun BottomShadow(alpha: Float = 0.2f, height: Dp = 12.dp) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(height)
        .background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color.Black.copy(alpha = alpha),
                    Color.Transparent,
                )
            )
        )
    )
}
