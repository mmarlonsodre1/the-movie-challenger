package com.example.data.repository.remote

import com.example.data.datasource.remote.SearchRemoteDataSource
import com.example.domain.model.search.SearchModel
import com.example.domain.repository.remote.SearchRepository
import kotlinx.coroutines.flow.Flow

class SearchRepositoryImpl(
    private val dataSource: SearchRemoteDataSource
): SearchRepository {
    override fun getSearch(query: String): Flow<SearchModel> = dataSource.getSearch(query)
}