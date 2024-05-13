package com.danchez.mercadolibrechallenge.data.utils

import java.io.IOException

/**
 * This exception is typically thrown when a request to a remote server returns an unsuccessful status code,
 * indicating that the request was not successful.
 */
class UnSuccessStatusException(override val message: String = "") : IOException(message)
