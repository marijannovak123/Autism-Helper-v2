package com.marijannovak.autismhelper.data.network

import com.marijannovak.autismhelper.common.errors.ErrorHandler
import com.marijannovak.autismhelper.data.models.domain.*
import com.marijannovak.autismhelper.data.models.requests.ParentPasswordRequest
import com.marijannovak.autismhelper.data.models.requests.UserUpdateRequest
import retrofit2.Response
import retrofit2.http.*

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

interface API {

    @GET("questions.json")
    suspend fun getQuestions(): List<QuizQuestion>

    @GET("categories.json")
    suspend fun getCategories(): List<QuizCategory>

    @GET("phrases.json")
    suspend fun getPhrases(): List<AacPhrase>

    @GET("phrase_categories.json")
    suspend fun getPhraseCategories(): List<PhraseCategory>

    @GET("users/{userId}.json")
    suspend fun getUser(@Path("userId") userId: String): Response<User?>

    @PUT("users/{userId}.json")
    suspend fun putUser(@Path("userId") userId: String, @Body user: User): Response<Unit>

    @PUT("users/{userId}/child_scores/{scoreId}.json")
    suspend fun putScore(@Path("userId") userId: String, @Path("scoreId") scoreId: Int, @Body score: ChildScore): Response<Unit>

    @PUT("users/{userId}/children/{childId}.json")
    suspend fun addChild(@Path("userId") userId: String, @Path("childId") childId: String, @Body child: Child): Response<Unit>

    @DELETE("users/{userId}/children/{childId}.json")
    suspend fun deleteChild(@Path("userId") userId: String, @Path("childId") childId: String): Response<Unit>

    @PATCH("users/{userId}.json")
    suspend fun updateParentPassword(@Path("userId") userId: String, @Body password: ParentPasswordRequest): Response<Unit>

    @PATCH("users/{userId}.json")
    suspend fun updateParent(@Path("userId") userId: String, @Body user: UserUpdateRequest): Response<Unit>

    @PATCH("users/{userId}/children/{childId}.json")
    suspend fun updateChild(@Path("userId") userId: String, @Path("childId") childId: String, @Body child: Child): Response<Unit>

    @GET
    @CustomConverterFactory.Xml
    suspend fun getFeed(@Url feedUrl: String): Response<RSS>

}