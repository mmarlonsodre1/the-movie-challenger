package com.example.domain.repository

import com.example.domain.model.gender.GenderListModel
import kotlinx.coroutines.flow.Flow

interface GenderRepository {
    fun getGenderList() : Flow<GenderListModel>
}