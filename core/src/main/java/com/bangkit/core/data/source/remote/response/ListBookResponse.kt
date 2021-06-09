package com.bangkit.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListBookResponse(
    @SerializedName("works")
    val works: List<BookResponse>
)
