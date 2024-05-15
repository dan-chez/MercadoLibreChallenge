package com.danchez.mercadolibrechallenge.presentation.product_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danchez.mercadolibrechallenge.domain.usecase.GetProductDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val getProductDetailsUseCase: GetProductDetailsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProductDetailsUIState>(ProductDetailsUIState.Initial)
    val uiState: StateFlow<ProductDetailsUIState> = _uiState.asStateFlow()

    fun getProductDetails(productId: String) {
        _uiState.value = ProductDetailsUIState.Loading
        viewModelScope.launch {
            getProductDetailsUseCase(productId = productId).onSuccess {
                _uiState.value = ProductDetailsUIState.Success(
                    details = it,
                )
            }.onFailure {
                // TODO Record error in crashlytics
                _uiState.value = ProductDetailsUIState.Failure
            }
        }
    }

    fun hideDialogs() {
        _uiState.value = ProductDetailsUIState.Initial
    }
}
