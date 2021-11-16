package com.opedarkgroup.features.admin.funcionarios.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opedarkgroup.data.api.RetrofitService
import com.opedarkgroup.data.models.admin.buscafuncionarios.BuscaFuncionariosResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class BuscaFuncionariosViewModel : ViewModel() {

    val disposable = CompositeDisposable()
    val buscaFuncionariosResult: MutableLiveData<List<BuscaFuncionariosResponse>> = MutableLiveData()
    private val error: MutableLiveData<String> = MutableLiveData()

    fun buscaFuncionarios() {
        disposable.add(
            RetrofitService.service.buscaFuncionarios()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    buscaFuncionariosResult.value = it
                }, { e ->
                    error.value = e.message
                })
        )
    }
}