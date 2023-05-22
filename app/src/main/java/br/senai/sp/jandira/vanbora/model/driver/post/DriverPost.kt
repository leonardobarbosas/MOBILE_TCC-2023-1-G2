package br.senai.sp.jandira.vanbora.model.driver.post

data class DriverPost(
    val avaliacao: Int,
    val cnh: String,
    val cpf: String,
    val data_nascimento: String,
    val descricao: String,
    val email: String,
    val foto: String,
    val id_preco: Int,
    val inicio_carreira: String,
    val nome: String,
    val rg: String,
    val senha: String,
    val telefone: String
)