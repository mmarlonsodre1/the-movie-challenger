package com.example.data_remote.datasource

import com.example.data.datasource.remote.GenderRemoteDataSource
import com.example.data_remote.mapper.gender.GenderMapper
import com.example.data_remote.service.GenderWebService
import com.example.data_remote.utils.RequestWrapper
import com.example.domain.model.gender.GenderListModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.KoinComponent
import org.koin.core.inject

class GenderRemoteDataSourceImpl(
    private val webService: GenderWebService
) : GenderRemoteDataSource, KoinComponent {
    private val requestWrapper: RequestWrapper by inject()

    override fun getGenderList(): Flow<GenderListModel> = flow {
        emit(
            GenderMapper.toDomain(
                requestWrapper.wrapperGenericResponse {
                    webService.getGenderList()
                }
            )
        )
    }
}