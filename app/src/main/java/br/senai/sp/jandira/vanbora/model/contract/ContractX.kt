package br.senai.sp.jandira.vanbora.model.contract

import br.senai.sp.jandira.vanbora.model.driver.Driver
import br.senai.sp.jandira.vanbora.model.user.User

data class ContractX(
    val escola: Escola,
    val id: Int,
    val idade_passageiro: Int,
    val motorista: Driver,
    val nome_passageiro: String,
    val tipo_contrato: TipoContrato,
    val tipo_pagamento: TipoPagamento,
    val usuario: User,
    val valo_contrato: Int
)