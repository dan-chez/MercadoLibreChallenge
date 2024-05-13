package com.danchez.mercadolibrechallenge.di.modules

import com.danchez.mercadolibrechallenge.domain.repository.ProductRepository
import com.danchez.mercadolibrechallenge.domain.repository.ProductRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryBindingsModule {

    @Binds
    abstract fun providesProductRepository(impl: ProductRepositoryImpl): ProductRepository
}
