package com.dicoding.booksapp.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.core.domain.model.Book
import com.bangkit.core.domain.usecase.BookUseCase

class DetailBookViewModel(private val bookUseCase: BookUseCase) : ViewModel() {

    private var detailId = MutableLiveData<String>()

    fun setFavoriteBook(book: Book, newStatus: Boolean) =
        bookUseCase.setFavoriteBook(book, newStatus)

    fun setSelectedDetailBook(id: String) {
        this.detailId.value = id
    }

    val detailbook = Transformations.switchMap(detailId) {
        bookUseCase.getDetailBook(it).asLiveData()
    }

}