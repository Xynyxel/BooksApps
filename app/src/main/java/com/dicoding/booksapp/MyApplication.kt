package com.dicoding.booksapp

import android.app.Application
import com.bangkit.core.di.databaseModule
import com.bangkit.core.di.networkModule
import com.bangkit.core.di.repositoryModule
import com.dicoding.booksapp.di.useCaseModule
import com.dicoding.booksapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}