package com.example.domain.repository

import com.example.domain.model.search.SearchModel
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun getSearch(query: String) : Flow<SearchModel>
}