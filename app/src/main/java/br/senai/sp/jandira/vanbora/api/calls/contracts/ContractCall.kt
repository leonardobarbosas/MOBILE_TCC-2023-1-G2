package br.senai.sp.jandira.vanbora.api.calls.contracts

import br.senai.sp.jandira.vanbora.model.contract.Contract
import br.senai.sp.jandira.vanbora.model.contract.ContractX
import br.senai.sp.jandira.vanbora.model.driver.Driver
import br.senai.sp.jandira.vanbora.model.driver.DriverList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ContractCall {

    //GET CONTRACT PER ID
    @GET("contracts/{id}")
    fun getContractId(@Path("id")id: String): Call<Contract>

    @GET("contract/{id}")
    fun getOneContractId(@Path("id")id: String): Call<ContractX>

}