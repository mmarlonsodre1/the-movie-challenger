package com.example.home_feature.pages.home

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import com.example.base_feature.core.BaseFragment
import com.example.base_feature.mapper.AppMovieDetailMapper
import com.example.base_feature.utils.navDirections
import com.example.domain.model.movie.MovieDetailModel
import com.example.home_feature.databinding.FragmentHomeBinding
import com.example.home_feature.navigation.HomeNavigation
import com.example.home_feature.pages.home.adapter.HomeFirstAdapter
import com.example.home_feature.pages.home.adapter.HomeItemModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: HomeViewModel by viewModels()
    val navigation: HomeNavigation by navDirections()
    private val adapter = HomeFirstAdapter(onClick = ::navigateToDetail)

    override fun onCreateViewBinding(inflater: LayoutInflater) =
        FragmentHomeBinding.inflate(inflater)

    override fun setupView() {
        super.setupView()
        binding.rvGenders.adapter = adapter
    }

    private fun updateItems(column: HomeItemModel) {
        val items = adapter.items.toMutableList()
        items.add(column)
        adapter.items = items
    }

    private fun navigateToDetail(model: MovieDetailModel) {
        navigation.goToMovieDetail(AppMovieDetailMapper.toAppModel(model))
    }

    override fun addObservers(owner: LifecycleOwner) {
        super.addObservers(owner)
        viewModel.moviesViewState.onPostValue(owner,
            onSuccess = {
                it.second.results?.let { movies ->
                    updateItems(HomeItemModel(it.first.title, movies))
                }
            },
            onError = {
                print("erorr")
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.resetViewStates()
    }
}