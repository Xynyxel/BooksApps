package com.bangkit.core.data

import com.bangkit.core.data.source.local.LocalDataSource
import com.bangkit.core.data.source.remote.RemoteDataSource
import com.bangkit.core.data.source.remote.network.ApiResponse
import com.bangkit.core.data.source.remote.response.BookResponse
import com.bangkit.core.data.source.remote.response.DetailBookResponse
import com.bangkit.core.domain.model.Book
import com.bangkit.core.domain.model.DetailBook
import com.bangkit.core.domain.repository.IBookRepository
import com.bangkit.core.utils.AppExecutors
import com.bangkit.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BookRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IBookRepository {

    override fun getAllBook(): Flow<Resource<List<Book>>> =
        object :
            NetworkBoundResource<List<Book>, List<BookResponse>>() {
            override fun loadFromDB(): Flow<List<Book>> {
                return localDataSource.getAllBook().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Book>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<BookResponse>>> =
                remoteDataSource.getAllBook()

            override suspend fun saveCallResult(data: List<BookResponse>) {
                val bookList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertBook(bookList)
            }
        }.asFlow()

    override fun getFavoriteBook(): Flow<List<Book>> {
        return localDataSource.getFavoriteBook().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteBook(book: Book, state: Boolean) {
        val tourismEntity = DataMapper.mapDomainToEntity(book)
        appExecutors.diskIO().execute { localDataSource.setFavoriteBook(tourismEntity, state) }
    }

    override fun getDetailBook(id: String): Flow<Resource<DetailBook>> =
        object : NetworkBoundResource<DetailBook, DetailBookResponse>() {

            override fun loadFromDB(): Flow<DetailBook> {
                return localDataSource.getDetailBook(id).map {
                    if (it != null) {
                        DataMapper.detailBookEntitiesToDomainDetailBook(it)
                    } else {
                        it
                    }
                }
            }

            override fun shouldFetch(data: DetailBook?): Boolean =
                data == null

            override suspend fun createCall(): Flow<ApiResponse<DetailBookResponse>> =
                remoteDataSource.getDetailBook(id)

            override suspend fun saveCallResult(data: DetailBookResponse) {
                val detailBook = DataMapper.detailBookResponsesToDetailEntities(data)
                localDataSource.insertdetailBook(detailBook)
            }

        }.asFlow()


}