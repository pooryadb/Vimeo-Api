package db.poorya.namavatest.base.architecture

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

abstract class BaseRepository {

    fun <T> callApi(networkCall: suspend () -> Response<T>) = flow {
        val response = networkCall()
        emit(response)
    }.flowOn(Dispatchers.IO)

}