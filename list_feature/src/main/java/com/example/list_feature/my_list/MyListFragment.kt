package com.example.list_feature.my_list

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import com.example.base_feature.adapters.MovieDetailSimilarsAdapter
import com.example.base_feature.core.BaseFragment
import com.example.list_feature.databinding.FragmentMyListBinding

class MyListFragment : BaseFragment<FragmentMyListBinding>() {
    private val viewModel: MyListViewModel by viewModels()

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
                val adapter = MovieDetailSimilarsAdapter(
                    items = it,
                    onClick = {}
                )
                binding.gvMovies.adapter = adapter
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.resetViewStates()
    }
}