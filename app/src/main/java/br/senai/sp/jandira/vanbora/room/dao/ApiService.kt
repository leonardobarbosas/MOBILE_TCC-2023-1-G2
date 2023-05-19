package br.senai.sp.jandira.vanbora.room.dao

import androidx.room.Dao
import br.senai.sp.jandira.vanbora.model.user.User
import br.senai.sp.jandira.vanbora.room.model.LoginCredentials
import retrofit2.http.Body
import retrofit2.http.POST

@Dao
interface ApiService {
    @POST("user/login")
    suspend fun loginUser(@Body credentials: LoginCredentials): User
}