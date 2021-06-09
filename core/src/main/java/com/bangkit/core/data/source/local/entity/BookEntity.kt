package com.bangkit.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book")
data class BookEntity(
    @PrimaryKey
    @ColumnInfo(name = "bookId")
    var bookId: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "cover")
    var cover: Int,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false

)
