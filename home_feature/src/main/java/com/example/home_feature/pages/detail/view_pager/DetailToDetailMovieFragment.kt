package com.example.home_feature.pages.detail.view_pager

import android.view.LayoutInflater
import com.example.base_feature.core.BaseFragment
import com.example.domain.model.movie.MovieDetailModel
import com.example.home_feature.databinding.FragmentDetailToDetailMovieBinding

class DetailToDetailMovieFragment(
    var model: MovieDetailModel
) : BaseFragment<FragmentDetailToDetailMovieBinding>() {

    override fun onCreateViewBinding(inflater: LayoutInflater) =
        FragmentDetailToDetailMovieBinding.inflate(inflater)

    override fun setupView() {
        super.setupView()
        with(binding) {
            tvOriginalTitle.text = model.originalTitle
            tvGender.text = model.genresString()
            tvYear.text = model.releaseDate
            tvCountrie.text = model.productionCountries
        }
    }
}