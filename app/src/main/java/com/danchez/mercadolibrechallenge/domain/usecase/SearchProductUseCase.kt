package com.danchez.mercadolibrechallenge.domain.usecase

import com.danchez.mercadolibrechallenge.domain.model.Products
import com.danchez.mercadolibrechallenge.domain.repository.ProductRepository
import javax.inject.Inject

class SearchProductUseCase @Inject constructor(
    private val repository: ProductRepository,
) {
    suspend operator fun invoke(query: String): Result<Products> = repository.searchProduct(query = query)
}
