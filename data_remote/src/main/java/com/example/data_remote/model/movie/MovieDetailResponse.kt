package com.example.data_remote.model.movie

import com.example.data_remote.model.gender.GenderResponse
import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @SerializedName("adult") val adult: Boolean?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("budget") val budget: Int?,
    @SerializedName("genres") val genres: List<GenderResponse>?,
    @SerializedName("homepage") val homepage: String?,
    @SerializedName("id") val id: Int?,
    @SerializedName("imdb_id") val imdbId: String?,
    @SerializedName("original_language") val originalLanguage: String?,
    @SerializedName("original_title") val originalTitle: String?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("popularity") val popularity: Double?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("production_companies") val productionCompanies: List<ProductionCompanieResponse>?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("revenue") val revenue: Int?,
    @SerializedName("runtime") val runtime: Int?,
    @SerializedName("title") val title: String?,
    @SerializedName("vote_average") val voteAverage: Double?,
    @SerializedName("production_countries") val productionCountriesList: List<ProductionCountrie>?
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