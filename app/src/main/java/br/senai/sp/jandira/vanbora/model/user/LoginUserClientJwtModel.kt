package br.senai.sp.jandira.vanbora.model.user

data class LoginUserClientJwtModel(
    val id: Int,
    val message: String,
    val token: String
)