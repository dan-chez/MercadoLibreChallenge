package com.danchez.mercadolibrechallenge.utils

fun Int.toMoneyFormat() : String  {
    val format = "%,d".format(this).replace(",", ".")
    return "$ $format"
}

fun Int.toDiscountFormat() : String  {
    val format = "%,d".format(this).replace(",", ".")
    return "$format% OFF"
}
