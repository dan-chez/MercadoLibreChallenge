package com.danchez.mercadolibrechallenge.domain.mapper

import com.danchez.mercadolibrechallenge.data.model.ProductDetailsResponse
import com.danchez.mercadolibrechallenge.domain.model.ProductDetails
import com.danchez.mercadolibrechallenge.utils.calculateDiscountWithOriginalPrice
import com.danchez.mercadolibrechallenge.utils.toDiscountFormat
import com.danchez.mercadolibrechallenge.utils.toMoneyFormat
import javax.inject.Inject

class ProductDetailsResponseToProductDetailsMapper @Inject constructor() :
    ModelMapper<ProductDetailsResponse, ProductDetails> {
    override fun map(input: ProductDetailsResponse): ProductDetails {
        val title = input.title ?: ""
        val freeShipping = input.shipping?.freeShipping == true
        val condition = input.condition ?: ""
        val originalPrice = input.originalPrice?.toInt()
        val price = input.price?.toInt()
        val discount = if (originalPrice != null && price != null) {
            price.calculateDiscountWithOriginalPrice(originalPrice).toDiscountFormat()
        } else null
        val pictures = input.pictures?.mapNotNull { it?.secureUrl } ?: emptyList()
        return ProductDetails(
            title = title,
            freeShipping = freeShipping,
            condition = condition,
            price = price?.toMoneyFormat() ?: "",
            originalPrice = originalPrice?.toMoneyFormat(),
            discount = discount,
            pictures = pictures,
        )
    }
}
