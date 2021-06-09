package com.bangkit.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailBook(
    var bookdetailId: String,
    var title: String,
    var first_sentence: String,
    var cover: Int?,
    val subjects: String,
    val first_publish_date: String
) : Parcelable