package com.danchez.mercadolibrechallenge.presentation.search_product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danchez.mercadolibrechallenge.domain.usecase.SearchProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchProductViewModel @Inject constructor(
    private val searchProductUseCase: SearchProductUseCase,
) : ViewModel() {

    /*private val _uiState = MutableLiveData<BetUiState>()
    val uiState: LiveData<BetUiState> = _uiState*/

    fun searchProduct(query: String) {
        viewModelScope.launch {
            searchProductUseCase(query).onSuccess {

            }.onFailure {

            }
        }
    }
}
