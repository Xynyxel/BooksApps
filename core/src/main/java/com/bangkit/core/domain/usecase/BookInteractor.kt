package com.bangkit.core.domain.usecase

import com.bangkit.core.data.Resource
import com.bangkit.core.domain.model.Book
import com.bangkit.core.domain.model.DetailBook
import com.bangkit.core.domain.repository.IBookRepository
import kotlinx.coroutines.flow.Flow

class BookInteractor(private val bookRepository: IBookRepository) : BookUseCase {
    override fun getAllBook() = bookRepository.getAllBook()

    override fun getFavoriteBook() = bookRepository.getFavoriteBook()

    override fun setFavoriteBook(book: Book, state: Boolean) =
        bookRepository.setFavoriteBook(book, state)

    override fun getDetailBook(id: String): Flow<Resource<DetailBook>> =
        bookRepository.getDetailBook(id)
}