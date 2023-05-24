package br.senai.sp.jandira.vanbora.api.calls.prices

import br.senai.sp.jandira.vanbora.model.prices.AllPrices
import retrofit2.Call
import retrofit2.http.GET

interface PricesCall {

    @GET("prices")
    fun getAllPrices(): Call<AllPrices>

}