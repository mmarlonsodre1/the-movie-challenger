package com.example.data_remote.mapper.search

import com.example.data_remote.mapper.DataRemoteMapper
import com.example.data_remote.mapper.movie.MovieDetailMapper
import com.example.data_remote.model.movie.MovieDetailResponse
import com.example.data_remote.model.search.SearchResponse
import com.example.domain.model.movie.MovieDetailModel
import com.example.domain.model.search.SearchModel

object SearchMapper : DataRemoteMapper<SearchResponse, SearchModel>() {

    override fun toDomain(data: SearchResponse) = SearchModel(
        page = data.page,
        results = listToDomain(data.results)
    )

    private fun listToDomain(list: List<MovieDetailResponse>?): List<MovieDetailModel> {
        val listResponse = mutableListOf<MovieDetailModel>()
        list?.forEach {
            listResponse.add(
                MovieDetailMapper.toDomain(it)
            )
        }
        return listResponse.toList()
    }
}