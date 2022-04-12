package com.example.data_remote.service

import com.example.data_remote.model.movie.MovieDetailResponse
import com.example.data_remote.model.movie.MovieListResponse
import com.example.data_remote.service.MovieWebService.Constants.MOVIE_DETAIL
import com.example.data_remote.service.MovieWebService.Constants.MOVIE_LIST
import com.example.data_remote.service.MovieWebService.Constants.LIST_ID
import com.example.data_remote.service.MovieWebService.Constants.MOVIE_ID
import retrofit2.http.*

interface MovieWebService {

    @GET(MOVIE_LIST)
    suspend fun getMovieList(
        @Path(LIST_ID) genderId: Int
    ): MovieListResponse
    
    @GET(MOVIE_DETAIL)
    suspend fun getMovieDetail(
        @Path(MOVIE_ID) movieId: Int
    ): MovieDetailResponse

    object Constants {
        const val LIST_ID = "list_id"
        const val MOVIE_ID = "movie_id"
        const val MOVIE_LIST = "movie/{$LIST_ID}/lists"
        const val MOVIE_DETAIL = "movie/{$MOVIE_ID}"
    }
}