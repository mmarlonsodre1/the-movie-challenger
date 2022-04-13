package com.example.di

import com.example.home_feature.pages.detail.MovieDetailViewModel
import com.example.home_feature.pages.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { HomeViewModel() }
    viewModel { MovieDetailViewModel() }
}
