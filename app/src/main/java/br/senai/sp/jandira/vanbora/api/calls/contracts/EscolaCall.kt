package br.senai.sp.jandira.vanbora.api.calls.contracts

import br.senai.sp.jandira.vanbora.model.contract.EscolaList
import retrofit2.Call
import retrofit2.http.GET

interface EscolaCall {

    @GET("schools")
    fun getAllSchools(): Call<EscolaList>

}