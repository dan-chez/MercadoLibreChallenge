package com.danchez.mercadolibrechallenge.presentation.search_product.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.danchez.mercadolibrechallenge.domain.model.Products.Product
import com.danchez.mercadolibrechallenge.presentation.common.LoadingDialog
import com.danchez.mercadolibrechallenge.presentation.common.SimpleErrorAlertDialog
import com.danchez.mercadolibrechallenge.presentation.search_product.SearchProductUIState
import com.danchez.mercadolibrechallenge.presentation.search_product.SearchProductViewModel

@Composable
fun ProductsListComposable(
    modifier: Modifier = Modifier,
    onItemPressed: (String) -> Unit,
    viewModel: SearchProductViewModel = hiltViewModel(),
) {

    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        SearchProductUIState.Failure -> {
            SimpleErrorAlertDialog(
                onConfirmation = {
                    viewModel.hideDialogs()
                }
            )
        }

        SearchProductUIState.Loading -> {
            LoadingDialog()
        }

        is SearchProductUIState.Success -> {
            LoadItemsList(
                modifier = modifier,
                (uiState as SearchProductUIState.Success).products.products,
                onItemPressed = onItemPressed,
            )
        }

        else -> {}
    }
}

@Composable
private fun LoadItemsList(
    modifier: Modifier,
    products: List<Product>,
    onItemPressed: (String) -> Unit,
) {
    Box(modifier = modifier) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(products) { product ->
                ProductItemComposable(
                    product = product,
                    onItemClick = {
                        onItemPressed(it.id)
                    }
                )
                Divider(color = Color.Black)
            }
        }
    }
}
