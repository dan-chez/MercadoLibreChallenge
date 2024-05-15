package com.danchez.mercadolibrechallenge.domain.repository

import com.danchez.mercadolibrechallenge.data.model.ProductDetailsResponse
import com.danchez.mercadolibrechallenge.data.model.ProductResponse
import com.danchez.mercadolibrechallenge.data.source.remote.ProductRemoteSource
import com.danchez.mercadolibrechallenge.domain.mapper.ModelMapper
import com.danchez.mercadolibrechallenge.domain.model.ProductDetails
import com.danchez.mercadolibrechallenge.domain.model.Products
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ProductRepositoryTest {

    @MockK
    private lateinit var productsMapper: ModelMapper<ProductResponse, Products>

    @MockK
    private lateinit var productDetailsMapper: ModelMapper<ProductDetailsResponse, ProductDetails>

    @MockK
    private lateinit var remoteSource: ProductRemoteSource

    @InjectMockKs
    private lateinit var productRepository: ProductRepositoryImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `searchProduct returns Success with products`() = runBlocking {
        val query = "celular"
        val response = ProductResponse(results = emptyList())

        val expectedProducts = Products(emptyList())

        coEvery { productsMapper.map(response) } returns expectedProducts

        coEvery { remoteSource.searchProduct(query) } returns Result.success(response)

        val result = productRepository.searchProduct(query)

        Assert.assertEquals(Result.success(expectedProducts), result)
    }

    @Test
    fun `searchProduct returns Result Failure when remote source throws exception`() = runBlocking {
        val query = "celular"
        val exception = Exception("Error")

        coEvery { remoteSource.searchProduct(query) } returns Result.failure(exception)

        val result = productRepository.searchProduct(query)

        Assert.assertTrue(result.isFailure)
    }

    @Test
    fun `getProductDetails returns Success with details`() = runBlocking {
        val productId = "MCO2052769056"
        val response = ProductDetailsResponse(
            title = "Bolso Morral Portail Hardley Puerto Usb Anti Robo 20l Color Negro",
            shipping = null,
            condition = "new",
            pictures = emptyList(),
            originalPrice = 169990,
            price = 129990,
        )

        val expected = ProductDetails(
            title = "Bolso Morral Portail Hardley Puerto Usb Anti Robo 20l Color Negro",
            discount = "23% OFF",
            condition = "Nuevo",
            pictures = emptyList(),
            originalPrice = "$ 169.990",
            price = "$ 129.990",
            freeShipping = false,
        )

        coEvery { productDetailsMapper.map(response) } returns expected

        coEvery { remoteSource.getProductDetails(productId) } returns Result.success(response)

        val result = productRepository.getProductDetails(productId)

        Assert.assertEquals(Result.success(expected), result)
    }

    @Test
    fun `getProductDetails returns Result Failure when remote source throws exception`() = runBlocking {
        val productId = ""
        val exception = Exception("Error")

        coEvery { remoteSource.getProductDetails(productId) } returns Result.failure(exception)

        val result = productRepository.getProductDetails(productId)

        Assert.assertTrue(result.isFailure)
    }
}
