package com.example.home_feature.pages.detail.view_pager

import android.view.LayoutInflater
import com.example.base_feature.core.BaseFragment
import com.example.base_feature.mapper.AppMovieDetailMapper
import com.example.base_feature.utils.navDirections
import com.example.domain.model.movie.MovieDetailModel
import com.example.home_feature.databinding.FragmentSimilarMoviesBinding
import com.example.home_feature.navigation.HomeNavigation
import com.example.base_feature.adapters.MovieDetailSimilarsAdapter

class SimilarMoviesFragment(
    private val movies: List<MovieDetailModel>
) : BaseFragment<FragmentSimilarMoviesBinding>() {
    val navigation: HomeNavigation by navDirections()

    override fun onCreateViewBinding(inflater: LayoutInflater) =
        FragmentSimilarMoviesBinding.inflate(inflater)

    override fun setupView() {
        super.setupView()

        val adapter = MovieDetailSimilarsAdapter(
            items = movies,
            onClick = {
                navigation.goToMovieDetail(
                    AppMovieDetailMapper.toAppModel(it)
                )
            }
        )
        binding.gvMovies.adapter = adapter
    }
}