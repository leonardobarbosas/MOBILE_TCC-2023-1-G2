package br.senai.sp.jandira.vanbora.api.calls.contracts

import br.senai.sp.jandira.vanbora.api.constants.ConstantsApi
import br.senai.sp.jandira.vanbora.model.contract.Contract
import br.senai.sp.jandira.vanbora.model.contract.ContractX
import retrofit2.Call
import retrofit2.http.*

interface ContractCall {

    //GET CONTRACT PER ID
    @GET("contracts/{id}")
    fun getContractId(@Path("id")id: String): Call<Contract>

    @GET("contract/{id}")
    fun getOneContractId(@Path("id")id: String): Call<ContractX>

    @Headers("Content-type: ${ConstantsApi.CONTENT_TYPE}")
    @POST("contract")
    fun postContract(@Body contract: ContractX): Call<ContractX>

}