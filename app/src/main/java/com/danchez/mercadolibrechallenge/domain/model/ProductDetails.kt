package com.danchez.mercadolibrechallenge.domain.model

data class ProductDetails(
    val title: String,
    val freeShipping: Boolean,
    val condition: String,
    val originalPrice: String?,
    val price: String,
    val discount: String?,
    val pictures: List<String>,
)
