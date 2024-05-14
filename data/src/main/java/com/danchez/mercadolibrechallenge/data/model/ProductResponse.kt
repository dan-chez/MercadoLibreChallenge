package com.danchez.mercadolibrechallenge.data.model

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    val results: List<Product?>? = null,
    val query: String? = null,
) {
    data class Product(
        @SerializedName("original_price")
        val originalPrice: Number? = null,
        val price: Number? = null,
        val thumbnail: String? = null,
        val title: String? = null,
    )
}
