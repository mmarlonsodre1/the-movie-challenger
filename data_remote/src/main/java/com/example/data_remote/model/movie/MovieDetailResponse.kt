package com.example.data_remote.model.movie

import com.example.data_remote.model.gender.GenderResponse
import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @SerializedName("adult") val adult: Boolean? = null,
    @SerializedName("backdrop_path") val backdropPath: String? = null,
    @SerializedName("budget") val budget: Int? = null,
    @SerializedName("genres") val genres: List<GenderResponse>? = listOf(),
    @SerializedName("homepage") val homepage: String? = null,
    @SerializedName("id") val id: Int? = null,
    @SerializedName("imdb_id") val imdbId: String? = null,
    @SerializedName("original_language") val originalLanguage: String? = null,
    @SerializedName("original_title") val originalTitle: String? = null,
    @SerializedName("overview") val overview: String? = null,
    @SerializedName("popularity") val popularity: Double? = null,
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("production_companies") val productionCompanies: List<ProductionCompanieResponse>? = listOf(),
    @SerializedName("release_date") val releaseDate: String? = null,
    @SerializedName("revenue") val revenue: Int? = null,
    @SerializedName("runtime") val runtime: Int? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("vote_average") val voteAverage: Double? = null,
    @SerializedName("production_countries") val productionCountriesList: List<ProductionCountrie>? = listOf()
) {
    fun productionCountries(): String {
        var string = ""
        productionCountriesList?.forEach {
            if (string.isBlank()) string = it.name ?: ""
            else string = "$string, ${it.name}"
        }
        return string
    }
}

data class ProductionCountrie(
    @SerializedName("name") val name: String?
)