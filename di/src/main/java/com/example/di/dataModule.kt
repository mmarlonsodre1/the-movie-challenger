package com.example.di

import com.example.data.repository.GenderRepositoryImpl
import com.example.data.repository.MovieRepositoryImpl
import com.example.data.repository.SearchRepositoryImpl
import com.example.domain.repository.GenderRepository
import com.example.domain.repository.MovieRepository
import com.example.domain.repository.SearchRepository
import org.koin.dsl.module

val dataModule = module {
    single<GenderRepository> { GenderRepositoryImpl(get()) }
    single<MovieRepository> { MovieRepositoryImpl(get()) }
    single<SearchRepository> { SearchRepositoryImpl(get()) }
}

