package com.example.data.repository

import com.example.data.datasource.remote.GenderRemoteDataSource
import com.example.domain.model.gender.GenderListModel
import com.example.domain.repository.GenderRepository
import kotlinx.coroutines.flow.Flow

class GenderRepositoryImpl(
    private val dataSource: GenderRemoteDataSource
): GenderRepository {
    override fun getGenderList(): Flow<GenderListModel> = dataSource.getGenderList()
}