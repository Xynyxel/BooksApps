package com.bangkit.core.domain.usecase

import com.bangkit.core.data.Resource
import com.bangkit.core.domain.model.Book
import com.bangkit.core.domain.model.DetailBook
import kotlinx.coroutines.flow.Flow

interface BookUseCase {
    fun getAllBook(): Flow<Resource<List<Book>>>
    fun getFavoriteBook(): Flow<List<Book>>
    fun setFavoriteBook(book: Book, state: Boolean)
    fun getDetailBook(id: String): Flow<Resource<DetailBook>>
}