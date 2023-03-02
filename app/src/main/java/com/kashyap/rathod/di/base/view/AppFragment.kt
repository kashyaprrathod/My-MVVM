package com.kashyap.rathod.di.base.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.kashyap.rathod.BR
import com.kashyap.rathod.di.base.vm.BaseViewModel
import com.kashyap.rathod.utils.loader.SpinnerLoader

abstract class AppFragment<baseBinding : ViewDataBinding, baseViewModel : BaseViewModel> : Fragment() {

    lateinit var binding: baseBinding
    lateinit var vm: baseViewModel
    private var progressDialog: SpinnerLoader? = null

    abstract fun createLayout(layoutData: (Int, baseViewModel) -> Unit)
    abstract fun observer()
    abstract fun init(savedInstanceState: Bundle?)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        createLayout { layout, baseViewModel ->
            if (!::binding.isInitialized) {
                binding = DataBindingUtil.inflate(layoutInflater, layout, container, false)
                vm = baseViewModel
                binding.setVariable(BR.vm, vm)
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
        init(savedInstanceState)
    }

    fun msgNormal(msg: String) {
//        MessageUtils.normal(requireContext(), msg)
    }

    fun msgSuccess(msg: String) {
//        MessageUtils.success(requireContext(), msg)
    }

    fun msgInfo(msg: String) {
//        MessageUtils.info(requireContext(), msg)
    }

    fun msgWarning(msg: String) {
//        MessageUtils.warning(requireContext(), msg)
    }

    fun msgError(msg: String) {
//        MessageUtils.error(requireContext(), msg)
    }

    //Progress dialog
    protected fun showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = SpinnerLoader(requireContext())
        }
        progressDialog?.show()
    }

    protected fun dismissProgressDialog() {
        if (progressDialog != null && progressDialog?.isShowing == true) {
            progressDialog?.dismiss()
            progressDialog = null
        }
    }
}