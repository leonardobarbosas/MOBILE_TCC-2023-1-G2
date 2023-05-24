package br.senai.sp.jandira.vanbora.model.user.post

data class PostUser(
    val cep: String,
    val cpf: String,
    val data_nascimento: String,
    val email: String,
    val foto: String,
    val nome: String,
    val numero: String,
    val rg: String,
    val senha: String,
    val telefone: String
)