package br.senai.sp.jandira.vanbora.api.calls.user

import br.senai.sp.jandira.vanbora.api.constants.ConstantsApi
import br.senai.sp.jandira.vanbora.model.user.LoginUserClientJwtModel
import br.senai.sp.jandira.vanbora.model.user.User
import br.senai.sp.jandira.vanbora.model.user.UserList
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserCall {

    @GET("user/{id}")
    fun getUserById(@Path("id") id: String): Call<User>

    //GET ALL USERS
    @GET("users")
    fun getAllUsers(): Call<UserList>

    @Headers("Content-type: ${ConstantsApi.CONTENT_TYPE}")
    @PUT("user/{id}")
    fun putUser(@Path("id") id: String, @Body user: User): Call<String>

    @Headers("Content-type: ${ConstantsApi.CONTENT_TYPE}")
    @POST("user")
    fun saveUser(@Body user: User): Call<User>

    @Headers("Content-type: ${ConstantsApi.CONTENT_TYPE}")
    @POST("user/login")
    fun loginUserClient(@Body user: User): Call<LoginUserClientJwtModel>

    @DELETE("user/{id}")
    fun deleteUser(@Path("id") id: Int): Call<String>
}