package com.kashyap.mvvm_3_0.di.ui.base

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.kashyap.mvvm_3_0.BR
import com.kashyap.mvvm_3_0.R
import org.aviran.cookiebar2.CookieBar

abstract class AppActivity<binding : ViewDataBinding, viewModel : AppViewModel> : AppCompatActivity() {

    lateinit var binding: binding
    lateinit var vm: viewModel

    abstract fun createBinding(): Pair<Int, viewModel>

    abstract fun onActivityCreated()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity = createBinding()
        binding = DataBindingUtil.setContentView(this, activity.first)
        vm = activity.second
        binding.setVariable(BR.vm, vm)
        onActivityCreated()
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
        CookieBar.build(this)
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