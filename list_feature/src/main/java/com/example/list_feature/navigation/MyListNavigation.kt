package com.example.list_feature.navigation

import com.example.base_feature.model.AppMovieDetailModel


interface MyListNavigation {
    fun goToMovieDetail(model: AppMovieDetailModel)
}