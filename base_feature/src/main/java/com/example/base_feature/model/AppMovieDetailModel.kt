package com.example.base_feature.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AppMovieDetailModel(
    val adult: Boolean?,
    val backdropPath: String?,
    val budget: Int?,
    val genres: List<AppGenderModel>?,
    val homepage: String?,
    val id: Int?,
    val imdbId: String?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val productionCompanies: List<AppProductionCompanieModel>?,
    val releaseDate: String?,
    val revenue: Int?,
    val runtime: Int?,
    val title: String?,
    val voteAverage: Double?,
    val productionCountries: String?
) : Parcelable