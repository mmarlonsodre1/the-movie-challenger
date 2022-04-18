package com.example.data_remote.service

import com.example.data_remote.model.gender.GenderListResponse
import com.example.data_remote.service.GenderWebService.Constants.GENDER_LIST
import retrofit2.http.GET

interface GenderWebService {

    @GET(GENDER_LIST)
    suspend fun getGenderList(): GenderListResponse

    object Constants {
        const val GENDER_LIST = "genre/movie/list"
    }
}