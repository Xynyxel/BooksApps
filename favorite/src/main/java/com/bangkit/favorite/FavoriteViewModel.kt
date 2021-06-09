package com.bangkit.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.core.domain.usecase.BookUseCase

class FavoriteViewModel(bookUseCase: BookUseCase) : ViewModel() {
    val favoriteBook = bookUseCase.getFavoriteBook().asLiveData()
}