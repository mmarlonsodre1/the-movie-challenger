package com.example.data_local.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun Any.toJson(): String = Gson().toJson(this)

inline fun <reified T> String.fromJson(): T? = try {
    Gson().getAdapter(TypeToken.get(T::class.java)).fromJson(this)
} catch (e: Exception) {
    null
}