package com.danchez.mercadolibrechallenge.data.di

import com.danchez.mercadolibrechallenge.data.service.ApiClient
import com.danchez.mercadolibrechallenge.data.service.ProductServiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class ServiceApiModule {

    @Provides
    @ApiBaseUrl
    fun provideApiUrl(): String = "https://api.mercadolibre.com/"

    @Provides
    fun providesProductServiceApi(
        @ApiBaseUrl apiBaseUrl: String,
    ): ProductServiceApi {
        return ApiClient.RequestBuilder<ProductServiceApi>().setBaseUrl(apiBaseUrl)
            .setApiService(ProductServiceApi::class.java)
            .build()
    }
}
