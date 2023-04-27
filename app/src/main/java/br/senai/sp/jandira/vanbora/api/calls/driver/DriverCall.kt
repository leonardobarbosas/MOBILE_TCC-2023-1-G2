package br.senai.sp.jandira.vanbora.api.calls.driver

import br.senai.sp.jandira.vanbora.api.constants.ConstantsApi
import br.senai.sp.jandira.vanbora.model.driver.Driver
import br.senai.sp.jandira.vanbora.model.driver.DriverList
import br.senai.sp.jandira.vanbora.model.driver.LoginDriverClientJwtModel
import br.senai.sp.jandira.vanbora.model.user.LoginUserClientJwtModel
import br.senai.sp.jandira.vanbora.model.user.User
import retrofit2.Call
import retrofit2.http.*

interface DriverCall {

    //GET USER BY ID
    @GET("driver/{id}")
    fun getDriverById(@Path("id")id: String): Call<Driver>

    //GET ALL USERS
    @GET("drivers")
    fun getAllDrivers(): Call<DriverList>

    @Headers("Content-type: ${ConstantsApi.CONTENT_TYPE}")
    @POST("driver")
    fun saveDriver(@Body driver: Driver): Call<Driver>

    @Headers("Content-type: ${ConstantsApi.CONTENT_TYPE}")
    @POST("driver/login")
    fun loginDriverClient(@Body driver: Driver): Call<LoginDriverClientJwtModel>

}