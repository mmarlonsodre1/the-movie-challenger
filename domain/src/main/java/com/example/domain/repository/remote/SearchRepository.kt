package com.example.domain.repository.remote

import com.example.domain.model.search.SearchModel
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun getSearch(query: String) : Flow<SearchModel>
}