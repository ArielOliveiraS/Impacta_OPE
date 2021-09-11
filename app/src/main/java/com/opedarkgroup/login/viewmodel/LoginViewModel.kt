package com.opedarkgroup.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.opedarkgroup.login.model.api.RetrofitService
import com.opedarkgroup.login.model.models.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel : ViewModel() {
    val loginResult: MutableLiveData<LoginResponse> = MutableLiveData()
    private val error: MutableLiveData<String> = MutableLiveData()


    fun getLogin() {
        viewModelScope.launch {
            runCatching {
                withContext(Dispatchers.IO) {
                    RetrofitService.service.getLogin()
                }
            }.onSuccess {
                loginResult.value = it
            }.onFailure { e ->
                error.value = e.message
            }
        }
    }
}