package com.kashyap.mvvm2.ui.main

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.kashyap.mvvm2.R
import com.kashyap.mvvm2.databinding.ActivityMainBinding
import com.kashyap.mvvm2.di.app.AppActivity
import com.kashyap.mvvm2.di.room.user.User

class MainActivity : AppActivity<ActivityMainBinding, MainActivityVM>() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun createBinding(): Pair<Int, MainActivityVM> {
        return Pair(R.layout.activity_main, ViewModelProvider(this)[MainActivityVM::class.java])
    }

    override fun onActivityCreated() {
        vm.setUserData(
            User(
                uId = 25,
                firstName = "",
                lastName = "",
            )
        )
    }
}