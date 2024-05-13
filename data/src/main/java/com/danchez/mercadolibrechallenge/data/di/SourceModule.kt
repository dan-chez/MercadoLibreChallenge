package com.danchez.mercadolibrechallenge.data.di

import com.danchez.mercadolibrechallenge.data.source.remote.ProductRemoteSource
import com.danchez.mercadolibrechallenge.data.source.remote.ProductRemoteSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class SourceModule {

    @Binds
    abstract fun providesProductRemoteSource(impl: ProductRemoteSourceImpl): ProductRemoteSource
}
