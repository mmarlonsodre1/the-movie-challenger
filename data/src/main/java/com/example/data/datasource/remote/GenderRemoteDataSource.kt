package com.example.data.datasource.remote

import com.example.domain.model.gender.GenderListModel
import kotlinx.coroutines.flow.Flow

interface GenderRemoteDataSource {
    fun getGenderList() : Flow<GenderListModel>
}