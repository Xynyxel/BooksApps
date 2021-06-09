package com.dicoding.booksapp.di

import com.bangkit.core.domain.usecase.BookInteractor
import com.bangkit.core.domain.usecase.BookUseCase
import com.dicoding.booksapp.detail.DetailBookViewModel
import com.dicoding.booksapp.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<BookUseCase> { BookInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailBookViewModel(get()) }
}