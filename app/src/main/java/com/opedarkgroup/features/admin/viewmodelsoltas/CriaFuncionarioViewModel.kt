package com.opedarkgroup.features.admin.viewmodelsoltas

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opedarkgroup.data.api.RetrofitService
import com.opedarkgroup.data.models.admin.criafuncionario.CriaFuncionarioBody
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CriaFuncionarioViewModel : ViewModel() {

    val disposable = CompositeDisposable()
    val criaFuncionarioResult: MutableLiveData<Boolean> = MutableLiveData()
    private val error: MutableLiveData<String> = MutableLiveData()

    fun criaFuncionario(criaFuncionarioBody: CriaFuncionarioBody) {
        disposable.add(
            RetrofitService.service.criaFuncionario(criaFuncionarioBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    criaFuncionarioResult.value = true
                }, { e ->
                    criaFuncionarioResult.value = false
                    error.value = e.message
                })
        )
    }
}