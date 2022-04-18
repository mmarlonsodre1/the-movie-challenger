package com.example.base_feature.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AppMovieDetailModel(
    val adult: Boolean? = null,
    val backdropPath: String? = null,
    val budget: Int? = null,
    val genres: List<AppGenderModel>? = null,
    val homepage: String? = null,
    val id: Int? = null,
    val imdbId: String? = null,
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val posterPath: String? = null,
    val productionCompanies: List<AppProductionCompanieModel>? = null,
    val releaseDate: String? = null,
    val revenue: Int? = null,
    val runtime: Int? = null,
    val title: String? = null,
    val voteAverage: Double? = null,
    val productionCountries: String? = null
) : Parcelable