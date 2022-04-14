package com.example.base_feature.mapper

import com.example.base_feature.model.AppGenderModel
import com.example.base_feature.model.AppMovieDetailModel
import com.example.base_feature.model.AppProductionCompanieModel
import com.example.domain.model.gender.GenderModel
import com.example.domain.model.movie.MovieDetailModel
import com.example.domain.model.movie.ProductionCompanieModel

object AppMovieDetailMapper : BaseFeatureMapper<MovieDetailModel, AppMovieDetailModel>() {

    override fun toAppModel(data: MovieDetailModel) = AppMovieDetailModel(
        adult = data.adult,
        backdropPath = data.backdropPath,
        budget = data.budget,
        genres = listGenresToApp(data.genres),
        homepage = data.homepage,
        id = data.id,
        imdbId = data.imdbId,
        originalLanguage = data.originalLanguage,
        originalTitle = data.originalTitle,
        overview = data.overview,
        popularity = data.popularity,
        posterPath = data.posterPath,
        productionCompanies = listProductionsToApp(data.productionCompanies),
        releaseDate = data.releaseDate,
        revenue = data.revenue,
        runtime = data.runtime,
        title = data.title,
        voteAverage = data.voteAverage,
        productionCountries = data.productionCountries
    )

    fun toDomainModel(data: AppMovieDetailModel) = MovieDetailModel(
        adult = data.adult,
        backdropPath = data.backdropPath,
        budget = data.budget,
        genres = listGenresToDomain(data.genres),
        homepage = data.homepage,
        id = data.id,
        imdbId = data.imdbId,
        originalLanguage = data.originalLanguage,
        originalTitle = data.originalTitle,
        overview = data.overview,
        popularity = data.popularity,
        posterPath = data.posterPath,
        productionCompanies = listProductionsToDomain(data.productionCompanies),
        releaseDate = data.releaseDate,
        revenue = data.revenue,
        runtime = data.runtime,
        title = data.title,
        voteAverage = data.voteAverage,
        productionCountries = data.productionCountries
    )

    private fun listProductionsToDomain(list: List<AppProductionCompanieModel>?): List<ProductionCompanieModel> {
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

    private fun listProductionsToApp(list: List<ProductionCompanieModel>?): List<AppProductionCompanieModel> {
        val listResponse = mutableListOf<AppProductionCompanieModel>()
        list?.forEach {
            listResponse.add(
                AppProductionCompanieModel(
                    id = it.id,
                    name = it.name,
                    logoPath = it.logoPath,
                    originCountry = it.originCountry
                )
            )
        }
        return listResponse.toList()
    }

    private fun listGenresToApp(list: List<GenderModel>?): List<AppGenderModel> {
        val listResponse = mutableListOf<AppGenderModel>()
        list?.forEach {
            listResponse.add(
                AppGenderModel(
                    id = it.id,
                    name = it.name
                )
            )
        }
        return listResponse.toList()
    }

    private fun listGenresToDomain(list: List<AppGenderModel>?): List<GenderModel> {
        val listResponse = mutableListOf<GenderModel>()
        list?.forEach {
            listResponse.add(
                GenderModel(
                    id = it.id,
                    name = it.name
                )
            )
        }
        return listResponse.toList()
    }
}