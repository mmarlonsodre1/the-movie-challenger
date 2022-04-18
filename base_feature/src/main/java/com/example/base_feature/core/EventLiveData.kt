package com.example.base_feature.core

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.example.base_feature.utils.ViewState
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

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

@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(o: T?) {
            data = o
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)

    try {
        afterObserve.invoke()

        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("Valor do LiveData não mudou")
        }

    } finally {
        this.removeObserver(observer)
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}

@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> ViewState<T>.assert(isSuccessAssert: Boolean = true) {
    if(this.status == ViewState.Status.LOADING) return
    if (isSuccessAssert) {
        kotlin.assert(this.error == null)
        kotlin.assert(this.status == ViewState.Status.SUCCESS)
        kotlin.assert(this.data != null)
    }  else {
        kotlin.assert(this.error != null)
        kotlin.assert(this.status == ViewState.Status.ERROR)
        kotlin.assert(this.data == null)
    }
}

@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<ViewState<T>>.assertNeutral() {
    assert(this.value?.status == ViewState.Status.NEUTRAL)
    assert(this.value?.error == null)
    assert(this.value?.data == null)
}

@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<T>.getOrAwaitValueAndAssert(
    isSuccessAssert: Boolean = true,
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {}
) {
    val value = this.getOrAwaitValue(time, timeUnit, afterObserve) as ViewState<*>
    value.assert(isSuccessAssert)
}
