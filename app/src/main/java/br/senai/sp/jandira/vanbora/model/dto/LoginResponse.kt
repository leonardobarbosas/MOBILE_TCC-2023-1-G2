package br.senai.sp.jandira.vanbora.model.dto

import br.senai.sp.jandira.vanbora.model.user.User

data class LoginResponse(
    var token: String,
    var data : User,
)