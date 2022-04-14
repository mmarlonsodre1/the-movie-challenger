package com.example.data_remote.mapper.movie

import com.example.data_remote.mapper.DataRemoteMapper
import com.example.data_remote.mapper.gender.GenderMapper
import com.example.data_remote.model.movie.MovieDetailResponse
import com.example.data_remote.model.movie.ProductionCompanieResponse
import com.example.domain.model.movie.MovieDetailModel
import com.example.domain.model.movie.ProductionCompanieModel

object MovieDetailMapper : DataRemoteMapper<MovieDetailResponse, MovieDetailModel>() {

    override fun toDomain(data: MovieDetailResponse) = MovieDetailModel(
        adult = data.adult,
        backdropPath = data.backdropPath,
        budget = data.budget,
        genres = GenderMapper.listToDomain(data.genres),
        homepage = data.homepage,
        id = data.id,
        imdbId = data.imdbId,
        originalLanguage = data.originalLanguage,
        originalTitle = data.originalTitle,
        overview = data.overview,
        popularity = data.popularity,
        posterPath = data.posterPath,
        productionCompanies = listToDomain(data.productionCompanies),
        releaseDate = data.releaseDate,
        revenue = data.revenue,
        runtime = data.runtime,
        title = data.title,
        voteAverage = data.voteAverage,
        productionCountries = data.productionCountries()
    )

    private fun listToDomain(list: List<ProductionCompanieResponse>?): List<ProductionCompanieModel> {
        val listResponse = mutableListOf<ProductionCompanieModel>()
        list?.forEach {
            listResponse.add(
                ProductionCompanieModel(
                    id = it.id,
                    name = it.name,
                    logoPath = it.logoPath,
                    originCountry = it.originCountry
                )
            )
        }
        return listResponse.toList()
    }
}