package com.opedarkgroup.features.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opedarkgroup.data.api.RetrofitService
import com.opedarkgroup.data.models.login.LoginBody
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginViewModel : ViewModel() {

    val disposable = CompositeDisposable()
    val loginResult: MutableLiveData<Boolean> = MutableLiveData()
    var funcionarioIdResult: MutableLiveData<Int> = MutableLiveData()
    private val error: MutableLiveData<String> = MutableLiveData()

    fun getLogin(loginBody: LoginBody) {
        disposable.add(
            RetrofitService.service.getLogin(loginBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    funcionarioIdResult.value = it.funcionarioId
                    loginResult.value = true
                }, { e ->
                    loginResult.value = false
                    error.value = e.message
                })
        )
    }
}