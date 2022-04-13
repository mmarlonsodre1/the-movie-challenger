package com.example.home_feature.navigation

import com.example.base_feature.model.AppMovieDetailModel


interface HomeNavigation {
    fun goToMovieDetail(model: AppMovieDetailModel)
}