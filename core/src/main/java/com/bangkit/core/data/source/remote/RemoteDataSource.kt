package com.bangkit.core.data.source.remote

import android.util.Log
import com.bangkit.core.data.source.remote.network.ApiResponse
import com.bangkit.core.data.source.remote.network.ApiService
import com.bangkit.core.data.source.remote.response.BookResponse
import com.bangkit.core.data.source.remote.response.DetailBookResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    suspend fun getAllBook(): Flow<ApiResponse<List<BookResponse>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getBookList()
                val dataArray = response.works
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.works))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailBook(id: String): Flow<ApiResponse<DetailBookResponse>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getDetailBook(id)
                Log.d("test doang", response.toString())
                if (response != null) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit((ApiResponse.Empty))
                }
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}
