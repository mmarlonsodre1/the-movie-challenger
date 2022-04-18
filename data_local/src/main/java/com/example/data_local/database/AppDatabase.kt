package com.example.data_local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data_local.database.dao.MovieDao
import com.example.data_local.database.model.MovieDetailDataLocal

@Database(entities = [MovieDetailDataLocal::class], version = 1)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}