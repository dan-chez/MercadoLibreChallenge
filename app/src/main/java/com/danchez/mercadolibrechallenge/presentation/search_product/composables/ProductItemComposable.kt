package com.danchez.mercadolibrechallenge.presentation.search_product.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.danchez.mercadolibrechallenge.R
import com.danchez.mercadolibrechallenge.domain.model.Products.Product
import com.danchez.mercadolibrechallenge.presentation.search_product.composables.preview.ProductItemProvider
import com.danchez.mercadolibrechallenge.presentation.theme.MercadoLibreTheme
import com.danchez.mercadolibrechallenge.presentation.theme.medium
import com.danchez.mercadolibrechallenge.presentation.theme.small

@Composable
fun ProductItemComposable(
    product: Product,
    onItemClick: (Product) -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        elevation = CardDefaults.cardElevation(0.dp),
        modifier = Modifier
            .clickable { onItemClick(product) }
            .fillMaxWidth()
            .background(color = Color.White)
            .height(150.dp),
    ) {
        Row(modifier = Modifier.padding(medium)) {
            ProductImage(productImageUrl = product.pictureUrl)
            ProductDescription(product = product)
        }
    }
}

@Composable
private fun ProductImage(productImageUrl: String) {
    SubcomposeAsyncImage(
        model = productImageUrl,
        contentDescription = stringResource(id = R.string.product_image_content_description),
        modifier = Modifier
            .fillMaxHeight()
            .width(120.dp),
        loading = {
            CircularProgressIndicator()
        }
    )
}

@Composable
private fun ProductDescription(product: Product) {
    Column(
        modifier = Modifier
            .fillMaxSize(1f)
            .padding(start = 8.dp),
    ) {
        Text(
            text = product.title,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black,
        )
        Spacer(modifier = Modifier.height(small))
        product.originalPrice?.let { originalPrice ->
            Text(
                text = originalPrice,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleSmall.copy(textDecoration = TextDecoration.LineThrough),
                color = Color.Gray,
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = product.price,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                color = Color.Black,
            )
            Spacer(modifier = Modifier.width(small))
            product.discount?.let { discount ->
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

@Preview
@Composable
private fun PreviewProductItemComposable(
    @PreviewParameter(ProductItemProvider::class) product: Product,
) {
    MercadoLibreTheme {
        ProductItemComposable(product = product, onItemClick = {})
    }
}
