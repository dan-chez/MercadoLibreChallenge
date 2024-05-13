package com.danchez.mercadolibrechallenge.domain.repository

import com.danchez.mercadolibrechallenge.data.model.ProductResponse
import com.danchez.mercadolibrechallenge.data.source.remote.ProductRemoteSource
import com.danchez.mercadolibrechallenge.domain.mapper.ModelMapper
import com.danchez.mercadolibrechallenge.domain.model.Products
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface ProductRepository {

    suspend fun searchProduct(query: String): Result<Products>
}

class ProductRepositoryImpl @Inject constructor(
    private val mapper: ModelMapper<ProductResponse, Products>,
    private val remoteSource: ProductRemoteSource,
) : ProductRepository {
    override suspend fun searchProduct(query: String): Result<Products> = kotlin.runCatching {
        withContext(Dispatchers.IO) {
            // TODO Check if there is something in local
            val response = remoteSource.searchProduct(query = query).getOrThrow()
            mapper.map(response)
        }
    }
}
