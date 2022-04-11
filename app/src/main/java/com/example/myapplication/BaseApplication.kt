package com.example.myapplication

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import br.com.havan.di.*
import br.com.havan.di.navigation.navigationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("unused")
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        startKoin {
            modules(
                navigationModule +
                listOf(
                    presentationModule,
                    domainModule,
                    dataModule,
                    dataRemoteModule,
                    dataLocalModule,
                    databaseModule
                )
            ).androidContext(applicationContext)
        }
    }
}