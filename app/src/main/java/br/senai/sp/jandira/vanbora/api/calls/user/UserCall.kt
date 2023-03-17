package br.senai.sp.jandira.vanbora.api.calls.user

import br.senai.sp.jandira.vanbora.api.constants.ConstantsApi
import br.senai.sp.jandira.vanbora.model.user.User
import br.senai.sp.jandira.vanbora.model.user.UserList
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface UserCall {

    //GET USER BY ID
    @GET("user/{id}")
    fun getUserById(@Path("id")id: Int): Call<User>

    //GET ALL USERS
    @GET("users")
    fun getAllUsers(): Call<UserList>
    //fun getAllUsers(): Call<List<UserModel>>

    @Headers("Content-type: ${ConstantsApi.CONTENT_TYPE}")
    @POST("user")
    fun saveUser(@Body user: User): Call<User>

    //DELETAR ALUNO
    @DELETE("endPoint")
    fun deleteUser(@Path("id")id: Int)
}