package com.example.base_feature.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.example.base_feature.utils.ViewState
import com.example.base_feature.utils.observeLiveData

interface ViewStateListener {
    fun onStateError(error: Throwable)

    fun onStateLoading()

    fun hideLoading()

    private fun <T> ViewState<T>.handle(
        onComplete: (() -> Unit)? = null,
        onSuccess: ((T) -> Unit)? = null,
    ) {
        stateHandler(
            onSuccess = {
                hideLoading()
                onSuccess?.invoke(it)
                onComplete?.invoke()
            },
            onError = { error ->
                hideLoading()
                onStateError(error)
                onComplete?.invoke()
            },
            loading = { onStateLoading() }
        )
    }

    fun <T> LiveData<ViewState<T>>.onPostValue(
        lifecycleOwner: LifecycleOwner,
        onComplete: (() -> Unit)? = null,
        onSuccess: ((T) -> Unit)? = null
    ) {
        observeLiveData(lifecycleOwner) {
            it.handle(onComplete, onSuccess)
        }
    }
}
