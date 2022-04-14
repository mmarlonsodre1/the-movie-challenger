package com.example.domain.repository.remote

import com.example.domain.model.gender.GenderListModel
import kotlinx.coroutines.flow.Flow

interface GenderRepository {
    fun getGenderList() : Flow<GenderListModel>
}