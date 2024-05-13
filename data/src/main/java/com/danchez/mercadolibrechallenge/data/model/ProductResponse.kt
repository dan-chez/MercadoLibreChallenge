package com.danchez.mercadolibrechallenge.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class ProductResponse(
    val results: List<Product?>? = null,
) {
    data class Product(
        @PrimaryKey val id: String,
        @SerializedName("accepts_mercadopago")
        val acceptsMercadoPago: Boolean? = null,
        @SerializedName("original_price")
        val originalPrice: Number? = null,
        val price: Number? = null,
        val thumbnail: String? = null,
        val title: String? = null,
    )
}
