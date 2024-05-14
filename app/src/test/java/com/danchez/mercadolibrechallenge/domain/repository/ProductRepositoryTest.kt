package com.danchez.mercadolibrechallenge.domain.repository

import com.danchez.mercadolibrechallenge.data.model.ProductResponse
import com.danchez.mercadolibrechallenge.data.source.remote.ProductRemoteSource
import com.danchez.mercadolibrechallenge.domain.mapper.ModelMapper
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
    private lateinit var mapper: ModelMapper<ProductResponse, Products>

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
        val response = ProductResponse(results = listOf())

        val expectedProducts = Products(listOf())

        coEvery { mapper.map(response) } returns expectedProducts

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
}
