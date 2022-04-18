package com.example.base_feature.dialogs

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.base_feature.databinding.BottomSheetGenericErrorBinding
import com.example.base_feature.utils.removeDrag
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.parcelize.Parcelize

class GenericErrorBottomSheet: BottomSheetDialogFragment() {
    private val args by lazy { Args.fromBundle(arguments) }

    private var mView: View? = null

    private var _binding: BottomSheetGenericErrorBinding? = null
        get() {
            if (field == null)
                field = BottomSheetGenericErrorBinding.inflate(layoutInflater)
            return field
        }

    private val binding: BottomSheetGenericErrorBinding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mView == null) {
            mView = binding.root
        } else {
            dismiss()
        }
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setErrorInformation()
    }

    override fun onResume() {
        super.onResume()
        removeDrag()
    }

    private fun setErrorInformation() {
        binding.btBack.setOnClickListener { dismiss() }
        binding.tvDescription.text = args.description
    }

    companion object {

        @Parcelize
        private data class Args(
            val description: String = "",
        ) : Parcelable {
            fun toBundle() = Bundle().also { it.putParcelable(ARGS, this) }

            companion object {
                private const val ARGS = "args"

                fun fromBundle(args: Bundle?) = args?.getParcelable(ARGS) ?: Args()
            }
        }

        fun newInstance(
            description: String = "",
        ): GenericErrorBottomSheet {
            val args = Args(
                description,
            )
            return GenericErrorBottomSheet().apply {
                arguments = args.toBundle()
            }
        }
    }
}