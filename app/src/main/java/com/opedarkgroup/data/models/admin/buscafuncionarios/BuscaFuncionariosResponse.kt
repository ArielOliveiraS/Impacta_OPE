package com.opedarkgroup.data.models.admin.buscafuncionarios

data class BuscaFuncionariosResponse(
    val id_funcionario_pk: Int,
    val nome: String,
    val usuario: String,
    val senha: String,
    val id_modulo_fk: Int,
    val email: String? = null
)