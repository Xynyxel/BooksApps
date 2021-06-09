package com.bangkit.core.data.source.remote.network

import com.bangkit.core.data.source.remote.response.DetailBookResponse
import com.bangkit.core.data.source.remote.response.ListBookResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/subjects/education.json")
    suspend fun getBookList(): ListBookResponse

    @GET("{key}.json")
    suspend fun getDetailBook(
        @Path("key") key : String
    ): DetailBookResponse
}