package com.danchez.mercadolibrechallenge.domain.usecase

import com.danchez.mercadolibrechallenge.domain.model.ProductDetails
import com.danchez.mercadolibrechallenge.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductDetailsUseCase @Inject constructor(
    private val repository: ProductRepository,
) {
    suspend operator fun invoke(productId: String): Result<ProductDetails> = repository.getProductDetails(productId = productId)
}
