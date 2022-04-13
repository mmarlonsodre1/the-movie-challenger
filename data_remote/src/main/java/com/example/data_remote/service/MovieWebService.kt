package com.example.data_remote.service

import com.example.data_remote.model.movie.MovieDetailResponse
import com.example.data_remote.model.movie.MovieListResponse
import com.example.data_remote.model.search.SearchResponse
import com.example.data_remote.service.MovieWebService.Constants.MOVIE_DETAIL
import com.example.data_remote.service.MovieWebService.Constants.MOVIE_ID
import com.example.data_remote.service.MovieWebService.Constants.MOVIE_POPULAR
import com.example.data_remote.service.MovieWebService.Constants.MOVIE_TOP
import com.example.data_remote.service.MovieWebService.Constants.MOVIE_TRENDING
import com.example.data_remote.service.MovieWebService.Constants.MOVIE_UPCOMING
import com.example.data_remote.service.MovieWebService.Constants.SIMILAR_MOVIES
import com.example.domain.model.search.SearchModel
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieWebService {

    @GET(MOVIE_TOP)
    suspend fun getTopMovies(): SearchResponse

    @GET(MOVIE_TRENDING)
    suspend fun getTrendingMovies(): SearchResponse

    @GET(MOVIE_POPULAR)
    suspend fun getPopularMovies(): SearchResponse

    @GET(MOVIE_UPCOMING)
    suspend fun getUpComingMovies(): SearchResponse

    @GET(MOVIE_DETAIL)
    suspend fun getMovieDetail(
        @Path(MOVIE_ID) movieId: Int
    ): MovieDetailResponse

    @GET(SIMILAR_MOVIES)
    suspend fun getSimilarMovies(
        @Path(MOVIE_ID) movieId: Int
    ): SearchResponse

    object Constants {
        const val MOVIE_ID = "movie_id"
        const val MOVIE_TOP = "movie/top_rated"
        const val MOVIE_TRENDING = "trending/movie/day"
        const val MOVIE_POPULAR = "movie/popular"
        const val MOVIE_UPCOMING = "movie/upcoming"
        const val MOVIE_DETAIL = "movie/{$MOVIE_ID}"
        const val SIMILAR_MOVIES = "movie/{$MOVIE_ID}/similar"
    }
}