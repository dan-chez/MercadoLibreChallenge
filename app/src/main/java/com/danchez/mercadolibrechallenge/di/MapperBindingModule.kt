package com.danchez.mercadolibrechallenge.di

import com.danchez.mercadolibrechallenge.data.model.ProductResponse
import com.danchez.mercadolibrechallenge.domain.mapper.ModelMapper
import com.danchez.mercadolibrechallenge.domain.mapper.ProductResponseToProductsMapper
import com.danchez.mercadolibrechallenge.domain.model.Products
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class MapperBindingModule {

    @Binds
    abstract fun providesProductResponseToProductsMapper(impl: ProductResponseToProductsMapper): ModelMapper<ProductResponse, Products>
}
