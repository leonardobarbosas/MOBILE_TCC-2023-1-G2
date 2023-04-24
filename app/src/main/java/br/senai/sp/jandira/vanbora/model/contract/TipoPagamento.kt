package br.senai.sp.jandira.vanbora.model.contract

data class TipoPagamento(
    val id: Int,
    val status_tipo_pagamento: Int,
    val tipo_pagamento: String
)