package com.kashyap.rathod.di.base.view

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.kashyap.rathod.BR
import com.kashyap.rathod.di.base.vm.BaseViewModel
import com.kashyap.rathod.utils.loader.SpinnerLoader

abstract class AppActivity<baseBinding : ViewDataBinding, baseViewModel : BaseViewModel> : AppCompatActivity() {

    private lateinit var binding: baseBinding
    private lateinit var vm: baseViewModel
    private var progressDialog: SpinnerLoader? = null

    abstract fun createLayout(layoutData: (Int, baseViewModel) -> Unit)
    abstract fun observer()
    abstract fun init(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createLayout { layout, baseViewModel ->
            if (!::binding.isInitialized) {
                binding = DataBindingUtil.setContentView(this, layout)
                vm = baseViewModel
                setContentView(binding.root)
                binding.setVariable(BR.vm, vm)
            }
        }
        observer()
        init(savedInstanceState)
        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        })
    }

    //Progress dialog
    protected fun showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = SpinnerLoader(this)
        }
        progressDialog?.show()
    }

    protected fun dismissProgressDialog() {
        if (progressDialog != null && progressDialog?.isShowing == true) {
            progressDialog?.dismiss()
            progressDialog = null
        }
    }

    //Custom message
    fun msgNormal(msg: String) {
        //Show snake bar with different background color
    }

    fun msgSuccess(msg: String) {
        //Show snake bar with different background color
    }

    fun msgInfo(msg: String) {
        //Show snake bar with different background color
    }

    fun msgWarning(msg: String) {
        //Show snake bar with different background color
    }

    fun msgError(msg: String) {
        //Show snake bar with different background color
    }
}