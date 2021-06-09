package com.bangkit.core.utils

import com.bangkit.core.data.source.local.entity.BookEntity
import com.bangkit.core.data.source.local.entity.DetailBookEntity
import com.bangkit.core.data.source.remote.response.BookResponse
import com.bangkit.core.data.source.remote.response.DetailBookResponse
import com.bangkit.core.domain.model.Book
import com.bangkit.core.domain.model.DetailBook

object DataMapper {
    fun mapResponsesToEntities(input: List<BookResponse>): List<BookEntity> {
        val bookList = ArrayList<BookEntity>()
        input.map {
            val book = BookEntity(
                bookId = it.key,
                cover = it.cover_id,
                title = it.title,
                isFavorite = false
            )
            bookList.add(book)
        }
        return bookList
    }

    fun mapEntitiesToDomain(input: List<BookEntity>): List<Book> =
        input.map {
            Book(
                bookId = it.bookId,
                cover = it.cover,
                title = it.title,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Book) = BookEntity(
        bookId = input.bookId,
        title = input.title,
        cover = input.cover,
        isFavorite = input.isFavorite
    )

    fun detailBookResponsesToDetailEntities(input: DetailBookResponse): DetailBookEntity {
        var first_sentence = ""
        val detailBookEntity = DetailBookEntity(
            bookdetailId = input.key.toString(),
            title = input.title.toString(),
            first_sentence = input.firstSentence?.value.toString(),
            cover = input.covers?.get(0),
            subjects = input.subjects?.joinToString().toString(),
            firstPublishDate = input.firstPublishDate.toString()
        )
        return detailBookEntity
    }

    fun detailBookEntitiesToDomainDetailBook(input: DetailBookEntity): DetailBook =
        DetailBook(
            bookdetailId = input.bookdetailId,
            title = input.title,
            first_sentence = input.first_sentence,
            cover = input.cover,
            subjects = input.subjects,
            first_publish_date = input.firstPublishDate
        )

    fun DomainToDetailBookEntity(input: DetailBook) = DetailBookEntity(
        bookdetailId = input.bookdetailId,
        title = input.title,
        cover = input.cover,
        first_sentence = input.first_sentence,
        subjects = input.subjects,
        firstPublishDate = input.first_publish_date.toString()
    )


}