package com.bangkit.core.domain.repository

import com.bangkit.core.data.Resource
import com.bangkit.core.domain.model.Book
import com.bangkit.core.domain.model.DetailBook
import kotlinx.coroutines.flow.Flow

interface IBookRepository {

    fun getAllBook(): Flow<Resource<List<Book>>>

    fun getFavoriteBook(): Flow<List<Book>>

    fun setFavoriteBook(book: Book, state: Boolean)

    fun getDetailBook(id: String) : Flow<Resource<DetailBook>>

}
