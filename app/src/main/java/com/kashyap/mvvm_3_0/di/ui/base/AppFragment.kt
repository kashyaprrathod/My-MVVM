package com.kashyap.mvvm_3_0.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.kashyap.mvvm_3_0.BR
import com.kashyap.mvvm_3_0.R
import com.kashyap.mvvm_3_0.di.ui.base.AppViewModel
import org.aviran.cookiebar2.CookieBar

abstract class AppFragment<binding : ViewDataBinding, viewModel : AppViewModel> : Fragment() {

    lateinit var binding: binding
    lateinit var vm: viewModel

    abstract fun createBinding(): Pair<Int, viewModel>

    abstract fun onFragmentCreated()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragment = createBinding()
        binding = DataBindingUtil.inflate(inflater, fragment.first, container, false)
        vm = fragment.second
        binding.setVariable(BR.vm, vm)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onFragmentCreated()
    }

    fun msgSuccess(msg: String) {
        //MessageUtils.success(this, msg)
        showCookie(msg, R.color.successColor)
    }

    fun msgInfo(msg: String) {
        //MessageUtils.info(this, msg)
        showCookie(msg, R.color.infoColor)
    }

    fun msgWarning(msg: String) {
        //MessageUtils.warning(this, msg)
        showCookie(msg, R.color.warningColor)
    }

    fun msgError(msg: String) {
        //MessageUtils.error(this, msg)
        showCookie(msg, R.color.errorColor)
    }

    //Set Cookie
    private fun showCookie(msg: String, backgroundColor: Int) {
        CookieBar.build(requireActivity())
            .setCustomView(R.layout.view_custom_toast)
            .setCustomViewInitializer {
                val tvMessage: TextView = it.findViewById(R.id.tv_message)
                tvMessage.isSelected = true
            }
            .setTitle(resources.getString(R.string.app_name))
            .setMessage(msg)
            .setBackgroundColor(backgroundColor)
            .setDuration(3000)
            .show()
    }
}