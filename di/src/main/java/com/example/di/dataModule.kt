package com.example.di

import com.example.data.repository.local.MovieDataLocalRepositoryImpl
import com.example.data.repository.remote.GenderRepositoryImpl
import com.example.data.repository.remote.MovieRepositoryImpl
import com.example.data.repository.remote.SearchRepositoryImpl
import com.example.domain.repository.local.MovieDataLocalRepository
import com.example.domain.repository.remote.GenderRepository
import com.example.domain.repository.remote.MovieRepository
import com.example.domain.repository.remote.SearchRepository
import org.koin.dsl.module

val dataModule = module {
    single<GenderRepository> { GenderRepositoryImpl(get()) }
    single<MovieRepository> { MovieRepositoryImpl(get()) }
    single<SearchRepository> { SearchRepositoryImpl(get()) }
    single<MovieDataLocalRepository> { MovieDataLocalRepositoryImpl(get()) }
}

