package com.example.di

import androidx.fragment.app.Fragment
import com.example.home_feature.navigation.HomeNavigation
import com.example.list_feature.navigation.MyListNavigation
import com.example.navigation.navigators.HomeNavigationImpl
import com.example.navigation.navigators.MyListNavigationImpl
import org.koin.dsl.module

val navigationModule = module {
    factory<HomeNavigation> { (fragment: Fragment) ->
        HomeNavigationImpl(fragment)
    }

    factory<MyListNavigation> { (fragment: Fragment) ->
        MyListNavigationImpl(fragment)
    }
}

