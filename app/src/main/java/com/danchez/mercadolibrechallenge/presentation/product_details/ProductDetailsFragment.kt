package com.danchez.mercadolibrechallenge.presentation.product_details

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import com.danchez.mercadolibrechallenge.databinding.FragmentProductDetailsBinding
import com.danchez.mercadolibrechallenge.presentation.base.BaseFragment
import com.danchez.mercadolibrechallenge.presentation.product_details.composables.ProductDetailsScreen
import com.danchez.mercadolibrechallenge.presentation.theme.MercadoLibreTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : BaseFragment<FragmentProductDetailsBinding>(FragmentProductDetailsBinding::inflate) {
    private lateinit var productId: String

    private val viewModel: ProductDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productId = arguments?.getString(PRODUCT_ID) ?: ""

        viewModel.getProductDetails(productId)

        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MercadoLibreTheme {
                    Scaffold(
                        content = { paddingValues ->
                            ProductDetailsScreen(modifier = Modifier.padding(paddingValues))
                        }
                    )
                }
            }
        }
    }

    companion object {
        const val PRODUCT_ID = "productId"
    }
}
