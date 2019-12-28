package com.marijannovak.autismhelper.common

import retrofit2.Response

typealias ApiExceptionResolver = (Int) -> Throwable

/**
 * Invoke an API call and resolve the data or throw exception if it failed
 * @param apiCall block of code invoking the API
 * @param exceptionResolver resolves unsuccessful call exception by status code
 */
suspend fun <T> apiRequest(
    exceptionResolver: ApiExceptionResolver = ErrorHandler.defaultApiExceptionResolver,
    apiCall: suspend () -> Response<T>): T {
        val response = apiCall.invoke()
        return if (response.isSuccessful) {
            response.body() ?: throw KotlinNullPointerException("Null response")
        } else {
            throw exceptionResolver(response.code())
        }
}