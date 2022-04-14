package com.example.domain.usecase.remote.gender

import com.example.domain.core.UseCase
import com.example.domain.model.gender.GenderListModel
import com.example.domain.repository.remote.GenderRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class GetGendersUserCase(
    scope: CoroutineScope,
    private val repository: GenderRepository
) : UseCase<GenderListModel, Unit>(scope) {

    override fun run(params: Unit?): Flow<GenderListModel> = repository.getGenderList()
}