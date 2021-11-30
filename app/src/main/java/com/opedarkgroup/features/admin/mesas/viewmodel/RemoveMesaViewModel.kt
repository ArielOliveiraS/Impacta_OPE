package com.opedarkgroup.features.admin.mesas.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opedarkgroup.data.api.API_KEY
import com.opedarkgroup.data.api.RetrofitService
import com.opedarkgroup.data.models.admin.removemesa.RemoveMesaBody
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RemoveMesaViewModel : ViewModel() {

    val disposable = CompositeDisposable()
    val removeMesaResult: MutableLiveData<Boolean> = MutableLiveData()
    private val error: MutableLiveData<String> = MutableLiveData()

    fun removeMesa(removeMesaBody: RemoveMesaBody) {
        disposable.add(
            RetrofitService.service.removeMesa(API_KEY, removeMesaBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    removeMesaResult.value = true
                }, { e ->
                    removeMesaResult.value = false
                    error.value = e.message
                })
        )
    }
}