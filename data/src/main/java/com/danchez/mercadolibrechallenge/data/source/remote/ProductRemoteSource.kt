package com.danchez.mercadolibrechallenge.data.source.remote

import com.danchez.mercadolibrechallenge.data.model.ProductDetailsResponse
import com.danchez.mercadolibrechallenge.data.model.ProductResponse
import com.danchez.mercadolibrechallenge.data.service.ProductServiceApi
import com.danchez.mercadolibrechallenge.data.utils.UnSuccessStatusException
import timber.log.Timber
import javax.inject.Inject

interface ProductRemoteSource {
    suspend fun searchProduct(query: String): Result<ProductResponse>

    suspend fun getProductDetails(productId: String): Result<ProductDetailsResponse>
}

class ProductRemoteSourceImpl @Inject constructor(
    private val serviceApi: ProductServiceApi,
) : ProductRemoteSource {

    override suspend fun searchProduct(query: String): Result<ProductResponse> {
        return kotlin.runCatching {
            val response = serviceApi.searchProducts(query = query)
            if (response.isSuccessful) {
                response.body()?.let { productResponse ->
                    Timber.i("Search products response: %s", productResponse.toString())
                    productResponse
                } ?: throw UnSuccessStatusException("null body response: $response")
            } else {
                Timber.e("Error searching products. Error: %s", response.errorBody().toString())
                throw UnSuccessStatusException()
            }
        }
    }

    override suspend fun getProductDetails(productId: String): Result<ProductDetailsResponse> {
        return kotlin.runCatching {
            val response = serviceApi.getProductDetails(productId = productId)
            if (response.isSuccessful) {
                response.body()?.let { productResponse ->
                    Timber.i("Product details response: %s", productResponse.toString())
                    productResponse
                } ?: throw UnSuccessStatusException("null body response: $response")
            } else {
                Timber.e("Error getting product details. Error: %s", response.errorBody().toString())
                throw UnSuccessStatusException()
            }
        }
    }
}
