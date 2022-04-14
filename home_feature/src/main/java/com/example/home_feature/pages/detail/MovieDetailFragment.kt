package com.example.home_feature.pages.detail

import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.example.base_feature.core.BaseFragment
import com.example.base_feature.model.AppMovieDetailModel
import com.example.base_feature.utils.loadUrl
import com.example.home_feature.databinding.FragmentMovieDetailBinding
import com.example.home_feature.pages.detail.adaper.MovieDetailViewPagerAdapter
import com.example.home_feature.pages.detail.view_pager.DetailToDetailMovieFragment
import com.example.home_feature.pages.detail.view_pager.SimilarMoviesFragment
import com.google.android.material.tabs.TabLayoutMediator

class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding>() {

    private val viewModel: MovieDetailViewModel by viewModels()
    private var pagerAdapter: MovieDetailViewPagerAdapter? = null
    private val fragments = mutableListOf<Fragment>()
    private val tabs = mutableListOf<String>()

    override fun onCreateViewBinding(inflater: LayoutInflater) =
        FragmentMovieDetailBinding.inflate(inflater)

    override fun setupView() {
        super.setupView()
        pagerAdapter = MovieDetailViewPagerAdapter(this)
        val model = arguments?.getParcelable<AppMovieDetailModel>("model")
        model?.let { model ->
            viewModel.getSimilarMovies(model.id!!)
            fragments.add(DetailToDetailMovieFragment(model))
            pagerAdapter?.items = fragments.toList()

            with(binding){
                btnBack.setOnClickListener {
                    findNavController().navigateUp()
                }
                ivMovieBlur.loadUrl(model.posterPath, hasBlur = true)
                ivMovie.loadUrl(model.posterPath)
                tvTitle.text = model.title
                tvGender.text = model.genresString()
                tvDescription.text = model.overview

                vpSimilars.adapter = pagerAdapter
                vpSimilars.setPageTransformer { page, _ ->
                    val wMeasureSpec =
                        View.MeasureSpec.makeMeasureSpec(page.width, View.MeasureSpec.EXACTLY)
                    val hMeasureSpec =
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
                    page.measure(wMeasureSpec, hMeasureSpec)
                    vpSimilars.measure(wMeasureSpec, hMeasureSpec)
                    vpSimilars.post {
                        vpSimilars.adapter?.notifyDataSetChanged()
                    }
                }
            }
        }
        tabs.add(getString(com.example.uikit.R.string.details))
    }

    override fun addObservers(owner: LifecycleOwner) {
        super.addObservers(owner)
        viewModel.similarMoviesViewState.onPostValue(owner,
            onSuccess = {
                it.results?.let { movies ->
                    if (movies.isNotEmpty()) {
                        tabs.add(0, getString(com.example.uikit.R.string.similar_movies))
                        fragments.add(0, SimilarMoviesFragment(movies))
                    }
                }
            },
            onError = {
                print("error")
            },
            onComplete = {
                pagerAdapter?.items = fragments
                binding.vpSimilars.adapter = pagerAdapter
                TabLayoutMediator(binding.tbMovie, binding.vpSimilars) { tab, position ->
                    tab.text = tabs[position]
                }.attach()
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.resetViewStates()
    }
}