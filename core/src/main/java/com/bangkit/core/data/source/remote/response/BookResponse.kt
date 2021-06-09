package com.bangkit.core.data.source.remote.response

import com.google.gson.annotations.SerializedName


data class BookResponse(
    @SerializedName("key")
    val key: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("cover_id")
    val cover_id: Int,
)