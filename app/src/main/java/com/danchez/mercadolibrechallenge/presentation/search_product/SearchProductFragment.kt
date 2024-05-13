package com.danchez.mercadolibrechallenge.presentation.search_product

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.danchez.mercadolibrechallenge.databinding.FragmentSearchProductBinding
import com.danchez.mercadolibrechallenge.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchProductFragment : BaseFragment<FragmentSearchProductBinding>(FragmentSearchProductBinding::inflate) {

    private val searchProductViewModel: SearchProductViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchProductViewModel.searchProduct("proteina")
    }
}