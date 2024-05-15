package com.danchez.mercadolibrechallenge.data.service

import com.danchez.mercadolibrechallenge.data.model.ProductDetailsResponse
import com.danchez.mercadolibrechallenge.data.model.ProductResponse
import com.danchez.mercadolibrechallenge.data.service.ServiceConstants.GET_PRODUCT_DETAILS
import com.danchez.mercadolibrechallenge.data.service.ServiceConstants.SEARCH_PRODUCTS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductServiceApi {

    @GET(SEARCH_PRODUCTS)
    suspend fun searchProducts(@Query("q") query: String): Response<ProductResponse?>

    @GET(GET_PRODUCT_DETAILS)
    suspend fun getProductDetails(@Path("productId") productId: String): Response<ProductDetailsResponse>
}
