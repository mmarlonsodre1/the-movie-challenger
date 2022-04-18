package com.example.base_feature.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleObserver
import com.example.base_feature.core.BaseFragment
import com.example.base_feature.databinding.DialogLoadingBinding

class LoadingDialog : DialogFragment(), LifecycleObserver {

    private var isLoading = false

    fun show(fragmentContainer: BaseFragment<*>){
        show(
            fragmentContainer.childFragmentManager,
            tag
        )
    }

    override fun show(manager: FragmentManager, tag: String?) {
        if (!isAdded || isLoading) {
            isLoading = true
            try {
                super.show(manager, tag)
            } catch (e: Exception) {
                return
            }
        }
    }

    override fun dismissAllowingStateLoss() {
        if (isLoading) {
            isLoading = false
            super.dismissAllowingStateLoss()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, com.example.uikit.R.style.FullScreenDialogStyle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDialogStyle()
    }

    private fun setupDialogStyle() {
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }


    var isLoadingVisible = true

    private var _binding: DialogLoadingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogLoadingBinding.inflate(LayoutInflater.from(context))
        return AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .create()
    }

    override fun onStart() {
        super.onStart()
        if (!isLoadingVisible) binding.dialogProgressBar.isVisible = false
        dialog?.window?.run {
            dialog?.setCancelable(false)
            setBackgroundDrawableResource(android.R.color.transparent)
            attributes = attributes.run { this }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}