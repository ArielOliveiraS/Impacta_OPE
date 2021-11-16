package com.opedarkgroup.features.admin.mesas.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opedarkgroup.data.api.RetrofitService
import com.opedarkgroup.data.models.comanda.buscamesas.MesaResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class BuscaMesasViewModel : ViewModel() {

    val disposable = CompositeDisposable()
    val buscaTodasAsMesasResult: MutableLiveData<List<MesaResponse>> = MutableLiveData()
    private val error: MutableLiveData<String> = MutableLiveData()

    fun buscaTodasAsMesas() {
        disposable.add(
            RetrofitService.service.buscaTodasAsMesas()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    buscaTodasAsMesasResult.value = it
                }, { e ->
                    error.value = e.message
                })
        )
    }
}