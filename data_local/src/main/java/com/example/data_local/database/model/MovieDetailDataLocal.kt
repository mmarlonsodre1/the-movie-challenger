package com.example.data_local.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.data_local.database.Converter

@Entity(tableName = "Movie")
data class MovieDetailDataLocal(
    @PrimaryKey val id: Int?,
    val adult: Boolean?,
    val backdropPath: String?,
    val budget: Int?,
    @TypeConverters(Converter::class) val genres: List<GenderDataLocal>?,
    val homepage: String?,
    val imdbId: String?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    @TypeConverters(Converter::class) val productionCompanies: List<ProductionCompanieDataLocal>?,
    val releaseDate: String?,
    val revenue: Int?,
    val runtime: Int?,
    val title: String?,
    val voteAverage: Double?,
    val productionCountries: String?
)