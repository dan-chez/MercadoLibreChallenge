package com.danchez.mercadolibrechallenge.domain.model

data class Products(
    val products: List<Product>,
) {
    data class Product(
        val pictureUrl: String,
        val originalPrice: String?,
        val price: String,
        val discount: String?,
        val title: String,
    )
}
