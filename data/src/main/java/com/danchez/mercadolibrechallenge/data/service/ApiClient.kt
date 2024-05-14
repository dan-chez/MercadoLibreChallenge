package com.danchez.mercadolibrechallenge.data.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    class RequestBuilder<T> {

        private var baseUrl: String? = null
        private var serviceApi: Class<T>? = null

        fun setBaseUrl(baseUrl: String): RequestBuilder<T> {
            this.baseUrl = baseUrl
            return this
        }

        fun setApiService(serviceApi: Class<T>): RequestBuilder<T> {
            this.serviceApi = serviceApi
            return this
        }

        fun build(): T {
            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(serviceApi!!)
        }
    }
}
