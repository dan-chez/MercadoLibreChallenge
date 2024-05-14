package com.danchez.mercadolibrechallenge.presentation.search_product

import com.danchez.mercadolibrechallenge.domain.model.Products

sealed interface SearchProductUIState {

    data object Initial : SearchProductUIState
    data class Success(
        val products: Products,
    ) : SearchProductUIState

    data object Failure : SearchProductUIState

    data object Loading : SearchProductUIState
}
