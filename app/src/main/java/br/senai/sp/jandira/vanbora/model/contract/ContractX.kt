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
        return "ContractX(id=$id, 'escola=$escola', 'idade_passageiro=$idade_passageiro', 'motorista=$motorista', 'nome_passageiro=$nome_passageiro', 'tipo_contrato=$tipo_contrato','tipo_pagamento=$tipo_pagamento', 'usuario=$usuario'"
    }
}

