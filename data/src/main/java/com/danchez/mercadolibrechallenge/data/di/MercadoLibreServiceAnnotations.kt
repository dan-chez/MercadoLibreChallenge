package com.danchez.mercadolibrechallenge.data.di

import javax.inject.Qualifier

/**
 * Use this annotation to provide a [String] with base url to call api services.
 */
@Retention(AnnotationRetention.BINARY)
@Qualifier
@MustBeDocumented
annotation class ApiBaseUrl
