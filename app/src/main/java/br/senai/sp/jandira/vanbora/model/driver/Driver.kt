package br.senai.sp.jandira.vanbora.model.driver

data class Driver(
    val avaliacao: Int = 0,
    val cnh: String = "",
    val cpf: String = "",
    val data_nascimento: String = "",
    val descricao: String = "",
    val email: String = "",
    val foto: String = "",
    val id: Int = 0,
    val inicio_carreira: String = "",
    val nome: String = "",
    val rg: String = "",
    val senha: String = "",
    val status_motorista: Int = 0,
    val telefone: String = "",
    val van: List<Van>? = null,
    val preco: List<Price>? = null
){
    override fun toString(): String {
        return "User(id=$id, 'avaliacao=$avaliacao', 'cnh=$cnh', 'cpf=$cpf', 'data_nascimento=$data_nascimento', 'descricao=$descricao', 'email=$email', 'foto=$foto', 'inicio_carreira=$inicio_carreira', 'nome=$nome', 'rg=$rg', 'senha=$senha', 'status_motorista=$status_motorista', 'telefone=$telefone', 'van=$van'"
    }
}