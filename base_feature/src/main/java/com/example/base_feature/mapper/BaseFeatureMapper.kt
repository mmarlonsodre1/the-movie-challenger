package com.example.base_feature.mapper

abstract class BaseFeatureMapper<in R, out D> {
    abstract fun toAppModel(data: R): D
}