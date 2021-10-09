package com.opedarkgroup.features.mesaslivres.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opedarkgroup.data.api.RetrofitService
import com.opedarkgroup.data.models.MesaResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class BuscaMesasLivresViewModel : ViewModel() {

    val disposable = CompositeDisposable()
    val buscarMesasLivresResult: MutableLiveData<List<MesaResponse>> = MutableLiveData()
    private val error: MutableLiveData<String> = MutableLiveData()

    fun buscarMesasLivres() {
        disposable.add(
            RetrofitService.service.buscarMesasLivres()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    buscarMesasLivresResult.value = it
                    Log.i("teste", "sucesso")
                }, { e ->
                    error.value = e.message
                    Log.i("teste", e.message ?: "erro")

                })
        )
    }
}