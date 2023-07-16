package com.example.newzapp.data.remote.helper

import com.example.newzapp.data.remote.NetworkResult
import retrofit2.Response

abstract class BaseApiResponse {

  suspend fun <T> safeApiCall(
    apiCall: suspend () -> Response<T>
  ): NetworkResult<T> {
    try {
      val response = apiCall()
      if (response.isSuccessful) {
        val body = response.body()
        body?.let { nnBody ->
          return NetworkResult.Success(nnBody)
        }
      }
      return error("${response.code()} ${response.message()} ${response.errorBody()}")
    } catch (exception: Exception) {
      return error("${exception.message} ${exception.localizedMessage} ${exception.printStackTrace()}")
    }
  }

  private fun <T> error(errorMessage: String): NetworkResult<T> =
    NetworkResult.Error(message = "Api call failed $errorMessage")
}