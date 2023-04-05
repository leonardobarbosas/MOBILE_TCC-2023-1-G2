package br.senai.sp.jandira.vanbora.api.calls.user

import br.senai.sp.jandira.vanbora.api.constants.ConstantsApi
import br.senai.sp.jandira.vanbora.model.user.Driver
import br.senai.sp.jandira.vanbora.model.user.DriverList
import retrofit2.Call
import retrofit2.http.*

interface DriverCall {

    //GET USER BY ID
    @GET("driver/{id}")
    fun getDriverById(@Path("id")id: Int): Call<Driver>

    //GET ALL USERS
    @GET("drivers")
    fun getAllDrivers(): Call<DriverList>

    @Headers("Content-type: ${ConstantsApi.CONTENT_TYPE}")
    @POST("driver")
    fun saveDriver(@Body driver: Driver): Call<Driver>

    //DELETAR ALUNO
    @DELETE("endPoint")
    fun deleteDriver(@Path("id")id: Int)

}