package com.danchez.mercadolibrechallenge.utils

fun Int.toMoneyFormat() : String  {
    val format = "%,d".format(this).replace(",", ".")
    return "$ $format"
}

fun Int.toDiscountFormat() : String  {
    val format = "%,d".format(this).replace(",", ".")
    return "$format% OFF"
}

fun Int.calculateDiscountWithOriginalPrice(originalPrice: Int): Int {
    val discountAmount = originalPrice - this
    val discountPercentage = (discountAmount.toDouble() / originalPrice.toDouble()) * 100

    return discountPercentage.toInt()
}
