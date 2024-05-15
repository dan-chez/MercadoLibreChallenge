package com.danchez.mercadolibrechallenge.data.model

import com.google.gson.annotations.SerializedName

data class ProductDetailsResponse(
    val title: String?,
    val shipping: Shipping?,
    val condition: String?,
    val pictures: List<Picture?>?,
    @SerializedName("original_price")
    val originalPrice: Number?,
    val price: Number?,
) {
    data class Shipping(
        @SerializedName("free_shipping")
        val freeShipping: Boolean?,
    )

    data class Picture(
        @SerializedName("secure_url")
        val secureUrl: String?,
    )
}
