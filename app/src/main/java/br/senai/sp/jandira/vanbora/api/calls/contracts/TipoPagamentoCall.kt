package br.senai.sp.jandira.vanbora.api.calls.contracts

import br.senai.sp.jandira.vanbora.model.contract.TipoPagamentoList
import retrofit2.Call
import retrofit2.http.GET

interface TipoPagamentoCall {

    @GET("typePayments")
    fun getAllPayments(): Call<TipoPagamentoList>

}