package com.example.domain.model.movie

import com.example.domain.model.gender.GenderModel

data class MovieDetailModel(
    val adult: Boolean?,
    val backdropPath: String?,
    val budget: Int?,
    val genres: List<GenderModel>,
    val homepage: String?,
    val id: Int?,
    val imdbId: String?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val productionCompanies: List<ProductionCompanieModel>?,
    val releaseDate: String?,
    val revenue: Int?,
    val runtime: Int?,
    val title: String?,
    val voteAverage: Double?,
    val productionCountries: String?
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