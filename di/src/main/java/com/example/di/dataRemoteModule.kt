package com.example.di

import com.example.data_remote.service.GenderWebService
import com.example.data_remote.service.MovieWebService
import com.example.data_remote.service.SearchWebService
import com.example.data_remote.utils.WebServiceFactory
import org.koin.dsl.module

val dataRemoteModule = module {
    single {
        WebServiceFactory.provideOkHttpClient(
            wasDebugVersion = true
        )
    }

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
}