package com.example.myapplication

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import com.example.di.*
import com.example.di.navigation.navigationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("unused")
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
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