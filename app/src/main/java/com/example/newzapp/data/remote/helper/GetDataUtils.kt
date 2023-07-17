package com.example.newzapp.data.remote.helper

import com.example.newzapp.data.mappers.Mapper.networkResponseToRoomEntity
import com.example.newzapp.data.remote.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

// T -> database  A -> remote
inline fun <reified T, reified A> performGetOperation(
  crossinline databaseQuery: () -> Flow<T>,
  noinline networkCall: suspend () -> NetworkResult<A>,
  noinline saveResultCall: suspend (A) -> Unit
): Flow<NetworkResult<T>> = flow<NetworkResult<T>> {
  emit(NetworkResult.Loading<T>())

  val localData = databaseQuery.invoke().first()
  emit(NetworkResult.Success(localData))

  val networkResult = networkCall.invoke()

  if (networkResult is NetworkResult.Success) {
    networkResult.data?.let { nnBody ->
      saveResultCall(nnBody)
      emit(NetworkResult.Success(nnBody.networkResponseToRoomEntity<T, A>()))
    }
  } else if (networkResult is NetworkResult.Error) {
    emit(NetworkResult.Error(message = networkResult.message.toString()))
    delay(500)
    emit(NetworkResult.Success(localData))
  }
}.flowOn(Dispatchers.IO)