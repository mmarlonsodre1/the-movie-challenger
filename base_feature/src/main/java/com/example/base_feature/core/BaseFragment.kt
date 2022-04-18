package com.example.base_feature.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import com.example.base_feature.dialogs.GenericErrorBottomSheet
import com.example.base_feature.dialogs.LoadingDialog
import com.example.base_feature.utils.showBottomSheet
import com.example.domain.exception.DataSourceException
import org.koin.core.KoinComponent

abstract class BaseFragment<Binding : ViewBinding> : Fragment(), ViewStateListener, KoinComponent {

    private var _binding: Binding? = null
        get() {
            if (field == null)
                field = onCreateViewBinding(layoutInflater)
            return field
        }

    protected val binding: Binding get() = _binding!!

    private var loadingDialogFragment: LoadingDialog? = null
        get() {
            if (field == null)
                field = LoadingDialog()

            return field
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        addObservers(viewLifecycleOwner)
    }

    abstract fun onCreateViewBinding(inflater: LayoutInflater): Binding

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    open fun addObservers(owner: LifecycleOwner) = Unit

    open fun setupView() = Unit

    override fun onStateError(error: Throwable) {
        hideLoading()
        if (error is DataSourceException) {
            GenericErrorBottomSheet.newInstance(
                description = error.message ?: getString(com.example.uikit.R.string.description_error)
            ).showBottomSheet(this@BaseFragment)
        }
    }

    override fun onStateLoading() {
        childFragmentManager.let {
            it.executePendingTransactions()
            if (loadingDialogFragment?.isAdded?.not() == true) {
                loadingDialogFragment?.show(this)
            }
        }
    }

    override fun hideLoading() {
        loadingDialogFragment?.dismissAllowingStateLoss()
        loadingDialogFragment = null
    }
}