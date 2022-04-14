package com.example.data_local.mapper

import com.example.data_local.database.model.GenderDataLocal
import com.example.data_local.database.model.MovieDetailDataLocal
import com.example.data_local.database.model.ProductionCompanieDataLocal
import com.example.domain.model.gender.GenderModel
import com.example.domain.model.movie.MovieDetailModel
import com.example.domain.model.movie.ProductionCompanieModel

object AppMovieDetailMapper {

    fun MovieDetailModel.toDao() = MovieDetailDataLocal(
        adult = adult,
        backdropPath = backdropPath,
        budget = budget,
        genres = listGenresToDao(genres),
        homepage = homepage,
        id = id,
        imdbId = imdbId,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        productionCompanies = listProductionsToDao(productionCompanies),
        releaseDate = releaseDate,
        revenue = revenue,
        runtime = runtime,
        title = title,
        voteAverage = voteAverage,
        productionCountries = productionCountries
    )

    private fun MovieDetailDataLocal.toDomain() = MovieDetailModel(
        adult = adult,
        backdropPath = backdropPath,
        budget = budget,
        genres = listGenresToDomain(genres),
        homepage = homepage,
        id = id,
        imdbId = imdbId,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        productionCompanies = listProductionsToDomain(productionCompanies),
        releaseDate = releaseDate,
        revenue = revenue,
        runtime = runtime,
        title = title,
        voteAverage = voteAverage,
        productionCountries = productionCountries
    )

    fun List<MovieDetailDataLocal>.listMoviesToDomain(): List<MovieDetailModel> {
        val listResponse = mutableListOf<MovieDetailModel>()
        this.forEach {
            listResponse.add(it.toDomain())
        }
        return listResponse.toList()
    }

    private fun listProductionsToDomain(list: List<ProductionCompanieDataLocal>?): List<ProductionCompanieModel> {
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

    private fun listProductionsToDao(list: List<ProductionCompanieModel>?): List<ProductionCompanieDataLocal> {
        val listResponse = mutableListOf<ProductionCompanieDataLocal>()
        list?.forEach {
            listResponse.add(
                ProductionCompanieDataLocal(
                    id = it.id,
                    name = it.name,
                    logoPath = it.logoPath,
                    originCountry = it.originCountry
                )
            )
        }
        return listResponse.toList()
    }

    private fun listGenresToDao(list: List<GenderModel>?): List<GenderDataLocal> {
        val listResponse = mutableListOf<GenderDataLocal>()
        list?.forEach {
            listResponse.add(
                GenderDataLocal(
                    id = it.id,
                    name = it.name
                )
            )
        }
        return listResponse.toList()
    }

    private fun listGenresToDomain(list: List<GenderDataLocal>?): List<GenderModel> {
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