package com.example.base_feature.utils

import android.widget.FrameLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

fun BottomSheetDialogFragment.removeDrag() {
    dialog?.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)?.run {
        BottomSheetBehavior.from(this).isDraggable = false
    }
}

fun DialogFragment.showBottomSheet(fragment: Fragment) {
    val tag = this::class.java.simpleName
    fragment.childFragmentManager.executePendingTransactions()

    if (fragment.childFragmentManager.findFragmentByTag(tag) == null) {
        try {
            show(fragment.childFragmentManager, tag)
        } catch (e: Exception) {
            Unit
        }
    }
}