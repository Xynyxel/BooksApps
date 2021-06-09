package com.bangkit.core.data.source.local.room

import androidx.room.*
import com.bangkit.core.data.source.local.entity.BookEntity
import com.bangkit.core.data.source.local.entity.DetailBookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Query("SELECT * FROM book")
    fun getAllBook(): Flow<List<BookEntity>>

    @Query("SELECT * FROM book where isFavorite = 1")
    fun getFavoriteBook(): Flow<List<BookEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: List<BookEntity>)

    @Update
    fun updateFavoriteBook(book: BookEntity)

    // Detail Book
    @Query("SELECT * FROM detailbook where bookdetailId=:id")
    fun getdetailBook(id: String): Flow<DetailBookEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertdetailBook(detailBook: DetailBookEntity)

}