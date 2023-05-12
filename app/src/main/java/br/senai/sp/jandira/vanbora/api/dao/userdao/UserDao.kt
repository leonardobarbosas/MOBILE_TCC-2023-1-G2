package br.senai.sp.jandira.vanbora.api.dao.userdao

import androidx.room.*
import br.senai.sp.jandira.vanbora.model_room.Usuario

@Dao
interface UserDao {

    @Query("SELECT * FROM tbl_usuario WHERE email = email AND senha = senha")
    fun getUserByEmailAndPassword(email: String, senha: String): Usuario?

}