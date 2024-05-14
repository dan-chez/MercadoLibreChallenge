package com.danchez.mercadolibrechallenge.presentation.search_product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danchez.mercadolibrechallenge.domain.usecase.SearchProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchProductViewModel @Inject constructor(
    private val searchProductUseCase: SearchProductUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<SearchProductUIState>(SearchProductUIState.Initial)
    val uiState: StateFlow<SearchProductUIState> = _uiState.asStateFlow()

    fun searchProduct(query: String) {
        _uiState.value = SearchProductUIState.Loading
        viewModelScope.launch {
            searchProductUseCase(query).onSuccess {
                _uiState.value = SearchProductUIState.Success(
                    products = it,
                )
            }.onFailure {
                // TODO Record error in crashlytics
                _uiState.value = SearchProductUIState.Failure
            }
        }
    }

    fun hideDialogs() {
        _uiState.value = SearchProductUIState.Initial
    }
}
