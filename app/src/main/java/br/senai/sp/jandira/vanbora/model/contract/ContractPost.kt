package br.senai.sp.jandira.vanbora.model.contract

data class ContractPost(
    val id_escola: Int,
    val id_motorista: Int,
    val id_tipo_contrato: Int,
    val id_tipo_pagamento: Int,
    val id_usuario: Int,
    val status_contrato: Int,
    val idade_passageiro: String,
    val nome_passageiro: String
)