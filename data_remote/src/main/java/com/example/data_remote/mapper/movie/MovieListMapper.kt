package com.example.data_remote.mapper.movie

import com.example.data_remote.mapper.DataRemoteMapper
import com.example.data_remote.model.movie.MovieItemListResponse
import com.example.data_remote.model.movie.MovieListResponse
import com.example.domain.model.movie.MovieItemListModel
import com.example.domain.model.movie.MovieListModel

object MovieListMapper : DataRemoteMapper<MovieListResponse, MovieListModel>() {

    override fun toDomain(data: MovieListResponse) = MovieListModel(
        id = data.id,
        page = data.page,
        results = listToDomain(data.results),
        totalPages = data.totalPages,
        totalResults = data.totalResults
    )

    private fun listToDomain(list: List<MovieItemListResponse>?): List<MovieItemListModel> {
        val listResponse = mutableListOf<MovieItemListModel>()
        list?.forEach {
            listResponse.add(
                MovieItemListModel(
                    description = it.description,
                    favoriteCount = it.favoriteCount,
                    id = it.id,
                    itemCount = it.itemCount,
                    iso_639_1 = it.iso_639_1,
                    listType = it.listType,
                    name = it.name,
                    posterPath = it.posterPath
                )
            )
        }
        return listResponse.toList()
    }
}