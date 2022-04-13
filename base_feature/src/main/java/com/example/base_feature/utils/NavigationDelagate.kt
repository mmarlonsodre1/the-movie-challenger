package com.example.base_feature.utils

import androidx.fragment.app.Fragment
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

inline fun <F : Fragment, reified V : Any> F.navDirections() = inject<V> {
    parametersOf(this)
}