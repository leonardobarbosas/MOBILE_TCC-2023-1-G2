package br.senai.sp.jandira.vanbora.api.calls.user

import br.senai.sp.jandira.vanbora.model.user.UserModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserCall {

    @GET("colocarEndPoint")
    fun getUserById(@Path("id")id: Int): Call<UserModel>

    @POST("users")
    fun saveUser(@Body userModel: UserModel): Call<UserModel>

    @DELETE("endPoint")
    fun deleteUser(@Path("id")id: Int)
}