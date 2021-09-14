package com.opedarkgroup.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opedarkgroup.login.model.api.RetrofitService
import com.opedarkgroup.login.model.models.LoginResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginViewModel : ViewModel() {

    val disposable = CompositeDisposable()
    val loginResult: MutableLiveData<Boolean> = MutableLiveData()
    private val error: MutableLiveData<String> = MutableLiveData()

    fun getLogin(loginResponse: LoginResponse) {
        disposable.add(
            RetrofitService.service.getLogin(loginResponse)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    loginResult.value = true
                }, { e ->
                    loginResult.value = false
                    error.value = e.message
                })
        )
    }
}