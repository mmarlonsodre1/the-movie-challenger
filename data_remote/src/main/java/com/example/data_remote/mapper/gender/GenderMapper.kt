package com.example.data_remote.mapper.gender

import com.example.data_remote.mapper.DataRemoteMapper
import com.example.data_remote.model.gender.GenderListResponse
import com.example.data_remote.model.gender.GenderResponse
import com.example.domain.model.gender.GenderListModel
import com.example.domain.model.gender.GenderModel

object GenderMapper : DataRemoteMapper<GenderListResponse, GenderListModel>() {

    override fun toDomain(data: GenderListResponse) = GenderListModel(
        genres = listToDomain(data.genres),
    )

    fun listToDomain(list: List<GenderResponse>?): List<GenderModel> {
        val listResponse = mutableListOf<GenderModel>()
        list?.forEach {
            listResponse.add(
                GenderModel(
                    id = it.id,
                    name = it.name
                )
            )
        }
        return listResponse.toList()
    }
}