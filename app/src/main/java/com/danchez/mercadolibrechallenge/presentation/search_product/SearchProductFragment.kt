package com.danchez.mercadolibrechallenge.presentation.search_product

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.danchez.mercadolibrechallenge.databinding.FragmentSearchProductBinding
import com.danchez.mercadolibrechallenge.presentation.base.BaseFragment
import com.danchez.mercadolibrechallenge.presentation.search_product.composables.ProductsListComposable
import com.danchez.mercadolibrechallenge.presentation.search_product.composables.SearchBarComposable
import com.danchez.mercadolibrechallenge.presentation.theme.MercadoLibreTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchProductFragment : BaseFragment<FragmentSearchProductBinding>(FragmentSearchProductBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MercadoLibreTheme {
                    Scaffold(
                        topBar = {
                            SearchBarComposable()
                        },
                        content = { paddingValues ->
                            ProductsListComposable(modifier = Modifier.padding(paddingValues))
                        }
                    )
                }
            }
        }
    }
}
