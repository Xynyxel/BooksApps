package com.bangkit.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bangkit.core.data.source.local.entity.BookEntity
import com.bangkit.core.data.source.local.entity.DetailBookEntity

@Database(
    entities = [BookEntity::class, DetailBookEntity::class],
    version = 1,
    exportSchema = false
)
abstract class BookDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao

}