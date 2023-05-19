package br.senai.sp.jandira.vanbora.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_user")
data class LoginCredentials(
    val id: Int,
    var email: String,
    var password: String
)




