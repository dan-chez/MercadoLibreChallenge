package com.danchez.mercadolibrechallenge.domain.mapper

import com.danchez.mercadolibrechallenge.data.model.ProductResponse
import com.danchez.mercadolibrechallenge.domain.model.Products
import com.danchez.mercadolibrechallenge.domain.model.Products.Product
import com.danchez.mercadolibrechallenge.utils.toDiscountFormat
import com.danchez.mercadolibrechallenge.utils.toMoneyFormat
import javax.inject.Inject

class ProductResponseToProductsMapper @Inject constructor() : ModelMapper<ProductResponse, Products> {
    override fun map(input: ProductResponse): Products {
        val results = checkNotNull(input.results)
        val products = results.mapNotNull {
            it?.let { product ->
                val originalPrice = product.originalPrice?.toInt()
                val price = product.price?.toInt()
                val discount = if (originalPrice != null && price != null) {
                    calculateDiscount(originalPrice, price).toDiscountFormat()
                } else null
                Product(
                    pictureUrl = product.thumbnail ?: "",
                    originalPrice = originalPrice?.toMoneyFormat(),
                    price = price?.toMoneyFormat() ?: "",
                    discount = discount,
                    title = product.title ?: "",
                )
            }
        }
        return Products(
            products = products,
        )
    }

    private fun calculateDiscount(originalPrice: Int, priceWithDiscount: Int): Int {
        val discountAmount = originalPrice - priceWithDiscount
        val discountPercentage = (discountAmount.toDouble() / originalPrice.toDouble()) * 100

        return discountPercentage.toInt()
    }
}
