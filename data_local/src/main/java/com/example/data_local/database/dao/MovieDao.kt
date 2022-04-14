package com.example.data_local.database.dao

import androidx.room.*
import com.example.data_local.database.model.MovieDetailDataLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovie(movie: MovieDetailDataLocal)

    @Query("SELECT * FROM Movie")
    fun getAllMovies() : Flow<List<MovieDetailDataLocal>>

    @Query("DELETE FROM Movie WHERE id = :movieId")
    fun deleteMovie(vararg movieId: Int)
}