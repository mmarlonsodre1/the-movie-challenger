package com.example.data_remote.datasource

import com.example.data.datasource.remote.MovieRemoteDataSource
import com.example.data_remote.mapper.movie.MovieDetailMapper
import com.example.data_remote.mapper.movie.MovieListMapper
import com.example.data_remote.service.MovieWebService
import com.example.data_remote.utils.RequestWrapper
import com.example.domain.model.movie.MovieDetailModel
import com.example.domain.model.movie.MovieListModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.KoinComponent
import org.koin.core.inject

class MovieRemoteDataSourceImpl(
    private val webService: MovieWebService
) : MovieRemoteDataSource, KoinComponent {
    private val requestWrapper: RequestWrapper by inject()

    override fun getMovieList(genderId: Int): Flow<MovieListModel> = flow {
        emit(
            MovieListMapper.toDomain(
                requestWrapper.wrapperGenericResponse {
                    webService.getMovieList(genderId)
                }
            )
        )
    }

    override fun getMovieDetail(movieId: Int): Flow<MovieDetailModel> = flow {
        emit(
            MovieDetailMapper.toDomain(
                requestWrapper.wrapperGenericResponse {
                    webService.getMovieDetail(movieId)
                }
            )
        )
    }
}