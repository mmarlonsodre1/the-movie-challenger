package com.example.list_feature.my_list

import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import com.example.base_feature.adapters.MovieDetailSimilarsAdapter
import com.example.base_feature.core.BaseFragment
import com.example.base_feature.mapper.AppMovieDetailMapper
import com.example.base_feature.utils.navDirections
import com.example.list_feature.databinding.FragmentMyListBinding
import com.example.list_feature.navigation.MyListNavigation

class MyListFragment : BaseFragment<FragmentMyListBinding>() {
    private val viewModel: MyListViewModel by viewModels()
    val navigation: MyListNavigation by navDirections()

    override fun onCreateViewBinding(inflater: LayoutInflater) =
        FragmentMyListBinding.inflate(inflater)

    override fun setupView() {
        super.setupView()
        viewModel.getMovies()
    }

    override fun addObservers(owner: LifecycleOwner) {
        super.addObservers(owner)
        viewModel.moviesViewState.onPostValue(owner,
            onSuccess = {
                if (it.isNotEmpty()) {
                    binding.tvEmpty.isVisible = false
                    binding.gvMovies.isVisible = true
                    val adapter = MovieDetailSimilarsAdapter(
                        items = it,
                        onClick = { movie ->
                            navigation.goToMovieDetail(AppMovieDetailMapper.toAppModel(movie))
                        }
                    )
                    binding.gvMovies.adapter = adapter
                }
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.resetViewStates()
    }
}