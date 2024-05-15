package com.danchez.mercadolibrechallenge.presentation.product_details.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.danchez.mercadolibrechallenge.presentation.common.LoadingDialog
import com.danchez.mercadolibrechallenge.presentation.common.SimpleErrorAlertDialog
import com.danchez.mercadolibrechallenge.presentation.product_details.ProductDetailsUIState
import com.danchez.mercadolibrechallenge.presentation.product_details.ProductDetailsViewModel

@Composable
fun ProductDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductDetailsViewModel = hiltViewModel(),
) {

    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        ProductDetailsUIState.Failure -> {
            SimpleErrorAlertDialog(
                onConfirmation = {
                    viewModel.hideDialogs()
                }
            )
        }

        ProductDetailsUIState.Loading -> LoadingDialog()
        is ProductDetailsUIState.Success -> {
            ProductDetailsComposable(details = (uiState as ProductDetailsUIState.Success).details)
        }

        else -> {}
    }
}
