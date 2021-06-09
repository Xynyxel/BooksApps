package com.bangkit.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detailbook")
data class DetailBookEntity(
    @PrimaryKey
    @ColumnInfo(name = "bookdetailId")
    var bookdetailId: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "first_sentence")
    var first_sentence: String,

    @ColumnInfo(name = "cover")
    var cover: Int?,

    @ColumnInfo(name = "subjects")
    val subjects: String,

    @ColumnInfo(name = "first_publish_date")
    val firstPublishDate: String
)