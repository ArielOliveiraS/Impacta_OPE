package com.opedarkgroup.features.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opedarkgroup.data.api.RetrofitService
import com.opedarkgroup.data.models.admin.resetsenha.ResetSenhaBody
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ResetSenhaViewModel : ViewModel() {

    val disposable = CompositeDisposable()
    val resetSenhaResult: MutableLiveData<Boolean> = MutableLiveData()
    private val error: MutableLiveData<String> = MutableLiveData()

    fun resetSenha(resetSenhaBody: ResetSenhaBody) {
        disposable.add(
            RetrofitService.service.resetSenha(resetSenhaBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    resetSenhaResult.value = true
                }, { e ->
                    resetSenhaResult.value = false
                    error.value = e.message
                })
        )
    }
}