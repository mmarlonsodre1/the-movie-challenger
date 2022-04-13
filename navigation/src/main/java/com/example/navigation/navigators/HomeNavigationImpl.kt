package com.example.navigation.navigators

import androidx.fragment.app.Fragment
import com.example.base_feature.model.AppMovieDetailModel
import com.example.home_feature.navigation.HomeNavigation
import com.example.navigation.R
import com.example.navigation.utils.navigate
import com.example.navigation.utils.setAnim

class HomeNavigationImpl(
    private val fragment: Fragment
): HomeNavigation {

    override fun goToMovieDetail(model: AppMovieDetailModel) {
        fragment.navigate(
            resId = R.id.movieDetailFragment,
            args = com.example.home_feature.pages.detail.MovieDetailFragmentArgs(model).toBundle(),
            navOptions = setAnim()
        )
    }
}