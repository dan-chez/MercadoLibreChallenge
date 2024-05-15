package com.danchez.mercadolibrechallenge.domain.repository

import com.danchez.mercadolibrechallenge.data.model.ProductDetailsResponse
import com.danchez.mercadolibrechallenge.data.model.ProductResponse
import com.danchez.mercadolibrechallenge.data.source.remote.ProductRemoteSource
import com.danchez.mercadolibrechallenge.domain.mapper.ModelMapper
import com.danchez.mercadolibrechallenge.domain.model.ProductDetails
import com.danchez.mercadolibrechallenge.domain.model.Products
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface ProductRepository {

    suspend fun searchProduct(query: String): Result<Products>

    suspend fun getProductDetails(productId: String): Result<ProductDetails>
}

class ProductRepositoryImpl @Inject constructor(
    private val searchProductMapper: ModelMapper<ProductResponse, Products>,
    private val productDetailsMapper: ModelMapper<ProductDetailsResponse, ProductDetails>,
    private val remoteSource: ProductRemoteSource,
) : ProductRepository {
    override suspend fun searchProduct(query: String): Result<Products> = kotlin.runCatching {
        withContext(Dispatchers.IO) {
            val response = remoteSource.searchProduct(query = query).getOrThrow()
            searchProductMapper.map(response)
        }
    }

    override suspend fun getProductDetails(productId: String): Result<ProductDetails> = kotlin.runCatching {
        withContext(Dispatchers.IO) {
            val response = remoteSource.getProductDetails(productId = productId).getOrThrow()
            productDetailsMapper.map(response)
        }
    }
}
