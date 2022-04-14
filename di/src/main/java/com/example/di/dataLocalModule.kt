package com.example.di

import com.example.data.datasource.local.MovieLocalDataSource
import com.example.data_local.datasource.MovieLocalDataSourceImpl
import org.koin.dsl.module

val dataLocalModule = module {
    single<MovieLocalDataSource> { MovieLocalDataSourceImpl(get()) }
}