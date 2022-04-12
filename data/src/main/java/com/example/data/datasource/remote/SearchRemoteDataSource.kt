package com.example.data.datasource.remote

import com.example.domain.model.search.SearchModel
import kotlinx.coroutines.flow.Flow

interface SearchRemoteDataSource {
    fun getSearch(query: String) : Flow<SearchModel>
}