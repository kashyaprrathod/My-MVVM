package com.kashyap.mvvm_3_0.presentation.screens.auth.login

import androidx.lifecycle.viewModelScope
import com.kashyap.mvvm_3_0.data.remote.Status
import com.kashyap.mvvm_3_0.domain.usecasses.LoginUseCases
import com.kashyap.mvvm_3_0.presentation.AppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginVM @Inject constructor() : AppViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state get() = _state

    @Inject
    lateinit var loginUseCase: LoginUseCases

    fun changeData(state: LoginState) {
        _state.value = state.copy(canLogin = isInputValid(state.email, state.password))
    }

    private fun isInputValid(email: String, password: String): Boolean {
        if (email.isEmpty()) {
            return false
        }
        if (password.isEmpty()) {
            return false
        }
        return true
    }

    fun handleIntent(loginIntents: LoginIntents) = viewModelScope.launch {
        when (loginIntents) {
            is LoginIntents.StartLogin -> {
                loginUseCase.loginNow().collect {
                    when (it.status) {
                        Status.SUCCESS -> {
                            _state.value = _state.value.copy(isLoggingIn = false)
                        }

                        Status.ERROR -> {
                            _state.value = _state.value.copy(isLoggingIn = false)
                        }

                        Status.WARN -> {
                            _state.value = _state.value.copy(isLoggingIn = false)
                        }

                        Status.LOADING -> {
                            _state.value = _state.value.copy(isLoggingIn = true)
                        }
                    }
                }
            }
        }
    }
}