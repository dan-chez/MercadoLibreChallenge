package com.danchez.mercadolibrechallenge.domain.mapper

import com.danchez.mercadolibrechallenge.data.model.ProductResponse
import com.danchez.mercadolibrechallenge.domain.model.Products
import com.danchez.mercadolibrechallenge.domain.model.Products.Product
import javax.inject.Inject

class ProductResponseToProductsMapper @Inject constructor() : ModelMapper<ProductResponse, Products> {
    override fun map(input: ProductResponse): Products {
        val results = checkNotNull(input.results)
        val products = results.mapNotNull {
            it?.let { product ->
                Product(
                    id = product.id,
                    acceptsMercadoPago = product.acceptsMercadoPago ?: false,
                    pictureUrl = product.thumbnail ?: "",
                    originalPrice = product.originalPrice?.toInt(),
                    price = product.price?.toInt() ?: 0,
                    title = product.title ?: "",
                )
            }
        }
        return Products(
            products = products,
        )
    }
}
