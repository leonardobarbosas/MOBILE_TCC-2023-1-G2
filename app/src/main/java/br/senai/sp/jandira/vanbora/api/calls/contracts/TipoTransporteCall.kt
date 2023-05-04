package br.senai.sp.jandira.vanbora.api.calls.contracts

import br.senai.sp.jandira.vanbora.model.contract.TipoTransporteList
import retrofit2.Call
import retrofit2.http.GET

interface TipoTransporteCall {

    @GET("typeContracts")
    fun getAllContracts(): Call<TipoTransporteList>

}