package br.senai.sp.jandira.vanbora.model_room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_usuario")
class Usuario {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    var email = ""
    var senha = ""
}