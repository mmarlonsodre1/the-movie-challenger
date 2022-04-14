package com.example.data_local.database

import android.text.TextUtils
import androidx.room.TypeConverter
import com.example.data_local.database.model.GenderDataLocal
import com.example.data_local.database.model.ProductionCompanieDataLocal
import com.example.domain.model.movie.MovieDetailModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class Converter {
    @TypeConverter
    fun movieDetailDataLocalToString(data: MovieDetailModel): String {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun stringToMovieDetailDataLocal(string: String): MovieDetailModel? {
        if (TextUtils.isEmpty(string))
            return null
        return Gson().fromJson(string, MovieDetailModel::class.java)
    }

    @TypeConverter
    fun genderDataLocalToString(data: List<GenderDataLocal>): String {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun stringToGenderDataLocal(string: String): List<GenderDataLocal>? {
        if (TextUtils.isEmpty(string))
            return null
        val listType: Type = object : TypeToken<List<GenderDataLocal?>?>() {}.type
        return Gson().fromJson(string, listType)
    }

    @TypeConverter
    fun productionCompanieDataLocalToString(data: List<ProductionCompanieDataLocal>): String {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun stringToProductionCompanieDataLocal(string: String): List<ProductionCompanieDataLocal>? {
        if (TextUtils.isEmpty(string))
            return null
        val listType: Type = object : TypeToken<List<ProductionCompanieDataLocal?>?>() {}.type
        return Gson().fromJson(string, listType)
    }
}