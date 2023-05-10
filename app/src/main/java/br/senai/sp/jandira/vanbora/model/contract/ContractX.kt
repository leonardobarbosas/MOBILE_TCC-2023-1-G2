package br.senai.sp.jandira.vanbora.model.contract

import br.senai.sp.jandira.vanbora.model.driver.Driver
import br.senai.sp.jandira.vanbora.model.user.User

data class ContractX(
    val escola: Escola,
    val id: Int = 0,
    val idade_passageiro: Int = 0,
    val motorista: Driver,
    val nome_passageiro: String = "",
    val tipo_contrato: TipoContrato,
    val tipo_pagamento: TipoPagamento,
    val usuario: User,
){
    override fun toString(): String {
        return "'id_escola=${escola.id}', 'idade_passageiro=$idade_passageiro', 'id_motorista=${motorista.id}', 'nome_passageiro=$nome_passageiro', 'id_tipo_contrato=${tipo_contrato.id}', 'id_tipo_pagamento=${tipo_pagamento.id}', 'id_usuario=${usuario.id}'"
    }
}

