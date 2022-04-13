package com.example.di.navigation

import androidx.fragment.app.Fragment
import com.example.home_feature.navigation.HomeNavigation
import com.example.navigation.navigators.HomeNavigationImpl
import org.koin.dsl.module

val navigationHomeModule = module {
    factory<HomeNavigation> { (fragment: Fragment) ->
        HomeNavigationImpl(fragment)
    }
}