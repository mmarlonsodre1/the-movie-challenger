package com.example.data_remote.datasource

import com.example.data.datasource.remote.SearchRemoteDataSource
import com.example.data_remote.mapper.search.SearchMapper
import com.example.data_remote.service.SearchWebService
import com.example.data_remote.utils.RequestWrapper
import com.example.domain.model.search.SearchModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.KoinComponent
import org.koin.core.inject

class SearchRemoteDataSourceImpl(
    private val webService: SearchWebService
) : SearchRemoteDataSource, KoinComponent {
    private val requestWrapper: RequestWrapper by inject()

    override fun getSearch(query: String): Flow<SearchModel> = flow {
        emit(
            SearchMapper.toDomain(
                requestWrapper.wrapperGenericResponse {
                    webService.getSearch(query)
                }
            )
        )
    }
}