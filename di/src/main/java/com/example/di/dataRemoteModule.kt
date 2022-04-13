package com.example.di

import com.example.data.datasource.remote.GenderRemoteDataSource
import com.example.data.datasource.remote.MovieRemoteDataSource
import com.example.data.datasource.remote.SearchRemoteDataSource
import com.example.data_remote.datasource.GenderRemoteDataSourceImpl
import com.example.data_remote.datasource.MovieRemoteDataSourceImpl
import com.example.data_remote.datasource.SearchRemoteDataSourceImpl
import com.example.data_remote.service.GenderWebService
import com.example.data_remote.service.MovieWebService
import com.example.data_remote.service.SearchWebService
import com.example.data_remote.utils.RequestWrapper
import com.example.data_remote.utils.RequestWrapperImpl
import com.example.data_remote.utils.WebServiceFactory
import org.koin.dsl.bind
import org.koin.dsl.module

val dataRemoteModule = module {
    single {
        WebServiceFactory.provideOkHttpClient(
            wasDebugVersion = true
        )
    }

    single { RequestWrapperImpl() } bind RequestWrapper::class

    single {
        WebServiceFactory.createWebService(
            get(),
        ) as GenderWebService
    }

    single {
        WebServiceFactory.createWebService(
            get(),
        ) as MovieWebService
    }

    single {
        WebServiceFactory.createWebService(
            get(),
        ) as SearchWebService
    }

    single<GenderRemoteDataSource> { GenderRemoteDataSourceImpl(get()) }
    single<MovieRemoteDataSource> { MovieRemoteDataSourceImpl(get()) }
    single<SearchRemoteDataSource> { SearchRemoteDataSourceImpl(get()) }
}