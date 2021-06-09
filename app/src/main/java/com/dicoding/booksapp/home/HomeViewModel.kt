package com.dicoding.booksapp.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.core.domain.usecase.BookUseCase

class HomeViewModel(bookUseCase: BookUseCase) : ViewModel() {
    val book = bookUseCase.getAllBook().asLiveData()
}