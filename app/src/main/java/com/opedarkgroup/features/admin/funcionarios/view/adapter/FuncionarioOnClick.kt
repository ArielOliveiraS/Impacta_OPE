package com.opedarkgroup.features.admin.funcionarios.view.adapter

import com.opedarkgroup.data.models.admin.buscafuncionarios.BuscaFuncionariosResponse

interface FuncionarioOnClick {
    fun onClick(funcionariosResponse: BuscaFuncionariosResponse)
}