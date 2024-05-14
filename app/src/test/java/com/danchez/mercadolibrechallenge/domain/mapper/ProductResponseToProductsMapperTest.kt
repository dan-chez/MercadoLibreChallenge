package com.danchez.mercadolibrechallenge.domain.mapper

import com.danchez.mercadolibrechallenge.data.model.ProductResponse
import com.danchez.mercadolibrechallenge.domain.model.Products
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ProductResponseToProductsMapperTest {

    @MockK
    private lateinit var mapper: ModelMapper<ProductResponse, Products>

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `map input to products`() {
        val product1 = ProductResponse.Product(title = "Product 1", price = 10000, originalPrice = 20000, thumbnail = "thumbnail1")
        val product2 = ProductResponse.Product(title = "Product 2", price = 15000, originalPrice = 25000, thumbnail = "thumbnail2")
        val productResponse = ProductResponse(results = listOf(product1, product2))

        val expectedProducts = listOf(
            Products.Product(
                pictureUrl = "thumbnail1",
                originalPrice = "20000",
                price = "10000",
                discount = "50%",
                title = "Product 1"
            ),
            Products.Product(
                pictureUrl = "thumbnail2",
                originalPrice = "250000",
                price = "150000",
                discount = "40%",
                title = "Product 2"
            )
        )

        every { mapper.map(any()) } returns Products(expectedProducts)

        val result = mapper.map(productResponse)

        Assert.assertEquals(expectedProducts, result.products)
    }
}
