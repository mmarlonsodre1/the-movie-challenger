package com.example.base_feature.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.example.base_feature.utils.ViewState
import com.example.base_feature.utils.observeLiveData

interface ViewStateListener {
    fun onStateError(error: Throwable)

    fun onStateLoading()

    fun hideLoading()

    fun handleError(error: Throwable)

    private fun <T> ViewState<T>.handle(
        onError: ((Throwable) -> Unit)? = null,
        onLoading: (() -> Unit)? = null,
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
                onError?.invoke(error) ?: onStateError(error)
                onComplete?.invoke()
            },
            loading = { onLoading?.invoke() ?: onStateLoading() }
        )
    }

    fun <T> LiveData<ViewState<T>>.onPostValue(
        lifecycleOwner: LifecycleOwner,
        onError: ((Throwable) -> Unit)? = null,
        onComplete: (() -> Unit)? = null,
        onLoading: (() -> Unit)? = null,
        onSuccess: ((T) -> Unit)? = null,
        errorBottomSheetAction: (() -> Unit)? = null,
        showDataSourceException: Boolean = true
    ) {
        observeLiveData(lifecycleOwner) {
            it.handle(onError, onLoading, onComplete, onSuccess)
        }
    }

}
