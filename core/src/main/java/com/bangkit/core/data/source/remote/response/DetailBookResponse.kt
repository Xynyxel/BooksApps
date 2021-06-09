package com.bangkit.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailBookResponse(

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("first_sentence")
	val firstSentence: FirstSentence? = null,

	@field:SerializedName("first_publish_date")
	val firstPublishDate: String? = null,

	@field:SerializedName("key")
	val key: String? = null,

	@field:SerializedName("covers")
	val covers: List<Int?>? = null,

	@field:SerializedName("authors")
	val authors: List<AuthorsItem?>? = null,

	@field:SerializedName("subjects")
	val subjects: List<String>? = null
)

data class FirstSentence(

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("value")
	val value: String? = null
)

data class Author(

	@field:SerializedName("key")
	val key: String? = null
)

data class AuthorsItem(

	@field:SerializedName("author")
	val author: Author? = null,

	@field:SerializedName("type")
	val type: Type? = null
)

data class Type(

	@field:SerializedName("key")
	val key: String? = null
)
