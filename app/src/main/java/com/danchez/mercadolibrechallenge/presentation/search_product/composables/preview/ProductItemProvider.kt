package com.danchez.mercadolibrechallenge.presentation.search_product.composables.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.danchez.mercadolibrechallenge.domain.model.Products

class ProductItemProvider : PreviewParameterProvider<Products.Product> {

    override val values: Sequence<Products.Product> = sequenceOf(
        getProduct()
    )

    private fun getProduct() = Products.Product(
        id = "",
        title = "Bolso Morral Portail Hardley Puerto Usb Anti Robo 20l Color Negro",
        price = "$ 129.990",
        pictureUrl = "",
        originalPrice = "$ 169.990",
        discount = "10%"
    )
}
