package com.opedarkgroup.features.comandaeletronica.mesasocupadas.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opedarkgroup.data.api.RetrofitService
import com.opedarkgroup.data.models.comanda.buscamesas.MesaResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class BuscaMesasOcupadasViewModel : ViewModel() {

    val disposable = CompositeDisposable()
    val buscarMesasOcupadasResult: MutableLiveData<List<MesaResponse>> = MutableLiveData()
    private val error: MutableLiveData<String> = MutableLiveData()

    fun buscarMesasOcupadas() {
        disposable.add(
            RetrofitService.service.buscarMesasOcupadas()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    buscarMesasOcupadasResult.value = it
                    Log.i("teste", "sucesso")
                }, { e ->
                    error.value = e.message
                    Log.i("teste", e.message ?: "erro")

                })
        )
    }
}