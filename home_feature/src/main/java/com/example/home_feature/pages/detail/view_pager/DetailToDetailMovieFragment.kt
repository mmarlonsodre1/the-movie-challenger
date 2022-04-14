package com.example.home_feature.pages.detail.view_pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.base_feature.core.BaseFragment
import com.example.base_feature.model.AppMovieDetailModel
import com.example.home_feature.R
import com.example.home_feature.databinding.FragmentDetailToDetailMovieBinding
import com.example.home_feature.databinding.FragmentMovieDetailBinding

class DetailToDetailMovieFragment(
    val model: AppMovieDetailModel
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