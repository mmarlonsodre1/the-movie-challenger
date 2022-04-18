package com.example.data_remote.service

import com.example.data_remote.model.search.SearchResponse
import com.example.data_remote.service.SearchWebService.Constants.QUERY
import com.example.data_remote.service.SearchWebService.Constants.SEARCH_MOVIE
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchWebService {

    @GET(SEARCH_MOVIE)
    suspend fun getSearch(
        @Query(QUERY) query: String,
    ): SearchResponse

    object Constants {
        const val SEARCH_MOVIE = "search/movie"
        const val QUERY = "query"
    }
}