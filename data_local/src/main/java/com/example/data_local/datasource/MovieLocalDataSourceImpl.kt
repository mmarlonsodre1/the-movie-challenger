package com.example.data_local.datasource

import com.example.data.datasource.local.MovieLocalDataSource
import com.example.data_local.database.dao.MovieDao
import com.example.data_local.mapper.AppMovieDetailMapper.listMoviesToDomain
import com.example.data_local.mapper.AppMovieDetailMapper.toDao
import com.example.domain.model.movie.MovieDetailModel
import kotlinx.coroutines.flow.flow

class MovieLocalDataSourceImpl(
    private val dao: MovieDao
) : MovieLocalDataSource {

    override fun addMovie(movie: MovieDetailModel) {
        dao.addMovie(movie = movie.toDao())
    }

    override fun getAllMovies() = flow {
        dao.getAllMovies().collect {
            emit(it.listMoviesToDomain())
        }
    }

    override fun deleteMovie(movieId: Int) {
        dao.deleteMovie(movieId)
    }

}