package com.danchez.mercadolibrechallenge.presentation.search_product

import com.danchez.mercadolibrechallenge.domain.model.Products
import com.danchez.mercadolibrechallenge.domain.usecase.SearchProductUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchProductViewModelTest {

    @MockK
    private lateinit var searchProductUseCase: SearchProductUseCase

    @InjectMockKs
    private lateinit var searchProductViewModel: SearchProductViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `searchProduct successful search updates UI state to Success`() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)

        val mockProducts = Products(emptyList())
        coEvery { searchProductUseCase("product") } returns Result.success(mockProducts)

        searchProductViewModel.searchProduct("product")

        val result = (searchProductViewModel.uiState.value as SearchProductUIState.Success).products

        Assert.assertEquals(result, mockProducts)
    }

    @Test
    fun `searchProduct failed search updates UI state to Failure`() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)

        coEvery { searchProductUseCase("product") } returns Result.failure(Exception())

        searchProductViewModel.searchProduct("product")

        val result = searchProductViewModel.uiState.value

        Assert.assertEquals(result, SearchProductUIState.Failure)
    }
}
