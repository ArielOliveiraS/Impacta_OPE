package com.opedarkgroup.features.admin.mesas.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opedarkgroup.data.api.API_KEY
import com.opedarkgroup.data.api.RetrofitService
import com.opedarkgroup.data.models.admin.adicionamesa.AdicionaMesaBody
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AdicionaMesaViewModel : ViewModel() {

    val disposable = CompositeDisposable()
    val adicionaMesaResult: MutableLiveData<Boolean> = MutableLiveData()
    private val error: MutableLiveData<String> = MutableLiveData()

    fun adicionaMesa(adicionaMesaBody: AdicionaMesaBody) {
        disposable.add(
            RetrofitService.service.adicionaMesa(API_KEY, adicionaMesaBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    adicionaMesaResult.value = true
                }, { e ->
                    adicionaMesaResult.value = false
                    error.value = e.message
                })
        )
    }
}