package com.opedarkgroup.features.listapedidos.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opedarkgroup.data.api.RetrofitService
import com.opedarkgroup.data.models.CategoriaProduto
import com.opedarkgroup.data.models.PedidoPorCategoria
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class BuscaProdutosViewModel : ViewModel() {

    val disposable = CompositeDisposable()
    val listaProdutosResult: MutableLiveData<List<PedidoPorCategoria>> = MutableLiveData()
    private val error: MutableLiveData<String> = MutableLiveData()

    fun buscarProdutosPorCategoria(categoriaProduto: CategoriaProduto) {
        disposable.add(
            RetrofitService.service.buscarProdutos(categoriaProduto)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listaProdutosResult.value = it
                }, { e ->
                    error.value = e.message
                })
        )
    }
}