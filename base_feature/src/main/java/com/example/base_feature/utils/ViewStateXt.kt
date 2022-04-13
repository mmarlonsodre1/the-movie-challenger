package com.example.base_feature.utils

import androidx.lifecycle.*
import com.example.base_feature.core.EventLiveData
import com.example.domain.core.UseCase
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class ViewState<T>(
    val status: Status = Status.NEUTRAL,
    val data: T? = null,
    val error: Throwable? = null
) {

    fun stateHandler(
        onSuccess: (T) -> Unit = {},
        onError: (Throwable) -> Unit = {},
        loading: () -> Unit = {},
    ) {
        when (status) {
            Status.SUCCESS -> data?.let { onSuccess(it) } ?: throw RuntimeException()
            Status.ERROR -> error?.let { onError(it) } ?: throw RuntimeException()
            Status.LOADING -> loading()
            else -> Unit
        }
    }

    enum class Status {
        SUCCESS, ERROR, LOADING, NEUTRAL
    }

}

fun <T> ViewState<T>?.isSuccess() = this?.status?.equals(ViewState.Status.SUCCESS) ?: false
fun <T> ViewState<T>?.isError() = this?.status?.equals(ViewState.Status.ERROR) ?: false
fun <T> ViewState<T>?.isLoading() = this?.status?.equals(ViewState.Status.LOADING) ?: false

fun <T> LiveData<ViewState<T>>.observeLiveData(
    lifecycleOwner: LifecycleOwner,
    isSingleEvent: Boolean = false,
    event: (ViewState<T>) -> Unit
) {
    observe(lifecycleOwner, Observer {
        (this as? EventLiveData)?.apply {
            getContent(isSingleEvent)?.let { event(it) }
            return@Observer
        }

        value?.let { event(it) }
    })
}

fun <T> viewState() = lazy {
    MutableLiveData<ViewState<T>>()
}

inline fun <V, reified U> V.useCase() where U : UseCase<*, *>, V : ViewModel, V : KoinComponent =
    inject<U> {
        parametersOf(viewModelScope)
    }

