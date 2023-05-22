package br.senai.sp.jandira.vanbora.roomData.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "login_data")
class LoginDataEntity {
    @PrimaryKey(autoGenerate = true)
    val id = 0
    val username = ""
    val password = ""
}