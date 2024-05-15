package com.danchez.mercadolibrechallenge.presentation.product_details

import com.danchez.mercadolibrechallenge.domain.model.ProductDetails

sealed interface ProductDetailsUIState {
    data object Initial : ProductDetailsUIState

    data class Success(
        val details: ProductDetails,
    ) : ProductDetailsUIState

    data object Failure : ProductDetailsUIState

    data object Loading : ProductDetailsUIState
}
