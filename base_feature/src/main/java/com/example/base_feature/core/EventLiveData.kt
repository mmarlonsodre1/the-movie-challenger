package com.example.base_feature.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.base_feature.utils.ViewState
import kotlinx.coroutines.flow.Flow

class EventLiveData<T> : MutableLiveData<T>() {

    private var hasBeenHandled = false

    private fun getContentIfNotHandled() =
        if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            value
        }

    fun getContent(isSingleEvent: Boolean) = if (isSingleEvent) getContentIfNotHandled() else value
}

fun <T> Flow<T>.asLiveData(): LiveData<ViewState<out T>> = liveData {
    try {
        collect {
            emit(
                ViewState(
                    ViewState.Status.SUCCESS,
                    it,
                    null
                )
            )
        }
    } catch (e: Exception) {
        emit(
            ViewState(
                ViewState.Status.ERROR,
                null,
                e
            )
        )
    }
}

fun <T> MutableLiveData<T>.asLiveData(): LiveData<T> = this

fun <T> MutableLiveData<ViewState<T>>.postSuccess(data: T) {
    postValue(ViewState(ViewState.Status.SUCCESS, data, null))
}

fun <T> MutableLiveData<ViewState<T>>.postError(error: Throwable?) {
    postValue(ViewState(ViewState.Status.ERROR, null, error))
}

fun <T> MutableLiveData<ViewState<T>>.postLoading() {
    postValue(ViewState(ViewState.Status.LOADING, null, null))
}

fun <T> MutableLiveData<ViewState<T>>.postNeutral() {
    value = ViewState(ViewState.Status.NEUTRAL, null, null)
}

