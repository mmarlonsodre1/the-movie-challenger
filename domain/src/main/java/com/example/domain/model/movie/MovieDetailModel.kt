package com.example.domain.model.movie

import com.example.domain.model.gender.GenderModel

data class MovieDetailModel(
    val adult: Boolean? = null,
    val backdropPath: String? = null,
    val budget: Int? = null,
    val genres: List<GenderModel>? = null,
    val homepage: String? = null,
    val id: Int? = null,
    val imdbId: String? = null,
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val posterPath: String? = null,
    val productionCompanies: List<ProductionCompanieModel>? = null,
    val releaseDate: String? = null,
    val revenue: Int? = null,
    val runtime: Int? = null,
    val title: String? = null,
    val voteAverage: Double? = null,
    val productionCountries: String? = null
) {
    fun genresString(): String? {
        if (genres.isNullOrEmpty()) return null
        if (genres?.size == 1) return genres.first().name

        var string = ""
        genres?.forEach { genderModel ->
            genderModel.name?.let { name ->
                string = if (string.isBlank()) name
                    else "$string, $name"
            }
        }
        string = string.trim()
        return string
    }
}