package br.senai.sp.jandira.vanbora.api.calls.user

import br.senai.sp.jandira.vanbora.model.user.UserModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserCall {

    //GET USER BY ID
    @GET("user/{id}")
    fun getUserById(@Path("id")id: Int): Call<UserModel>

    //GET ALL USERS
    @GET("users")
    fun getAllUsers(): Call<List<UserModel>>

    //ATUALIZAR USER
    @POST("user")
    fun saveUser(@Body userModel: UserModel): Call<UserModel>

    //DELETAR ALUNO
    @DELETE("endPoint")
    fun deleteUser(@Path("id")id: Int)
}