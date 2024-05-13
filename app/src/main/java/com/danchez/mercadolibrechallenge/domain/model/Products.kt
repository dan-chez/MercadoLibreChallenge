package com.danchez.mercadolibrechallenge.domain.model

data class Products(
    val products: List<Product>,
) {
    data class Product(
        val id: String,
        val pictureUrl: String,
        val originalPrice: Int?,
        val price: Int,
        val title: String,
        val acceptsMercadoPago: Boolean,
    )
}
