package com.bangkit.core.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Book(
    val bookId: String,
    val title: String,
    val cover: Int,
    val isFavorite: Boolean
) : Parcelable