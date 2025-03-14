package com.kashyap.mvvm_3_0.di.ui.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.kashyap.mvvm_3_0.BR
import com.kashyap.mvvm_3_0.R

class BaseCustomBottomSheetDialog<V : ViewDataBinding>(
    context: Context,
    private val layoutId: Int,
    private val listener: Listener,
    private val isFullHeight: Boolean = false
) : BottomSheetDialog(
    context,
    R.style.BottomSheetDialogTheme
) {
    private var binding: V? = null

    fun getBinding(): V? {
        init()
        return binding
    }

    private fun init() {
        if (binding == null) binding =
            DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, null, false)
        binding?.setVariable(BR.listener, listener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        binding?.root?.let { setContentView(it) }
        this.setOnShowListener { dialog ->
            val bottomSheetDialog = dialog as BottomSheetDialog
            val bottomSheetInternal: FrameLayout = bottomSheetDialog.findViewById(com.google.android.material.R.id.design_bottom_sheet)!!
            bottomSheetInternal.setBackgroundResource(R.drawable.bg_bottom_sheet)
            bottomSheetInternal.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            if (isFullHeight) {
                bottomSheetInternal.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
            }
            bottomSheetInternal.requestLayout()

            bottomSheetDialog.behavior.maxWidth = ViewGroup.LayoutParams.MATCH_PARENT
            if (isFullHeight) {
                bottomSheetDialog.behavior.maxHeight = ViewGroup.LayoutParams.MATCH_PARENT
                bottomSheetDialog.behavior.isDraggable = false
            }
            bottomSheetDialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    //Make sheet un removable
    fun makeUnRemovable() {
        setCancelable(false)
        setCanceledOnTouchOutside(false)
    }

    fun makeRemovable() {
        setCancelable(true)
        setCanceledOnTouchOutside(true)
    }

    interface Listener {
        fun onViewClick(view: View)
    }
}