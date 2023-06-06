package br.senai.sp.jandira.vanbora.model.user

data class User(
    var cep: String= "",
    var cpf: String= "",
    val data_nascimento: String = "",
    var email: String = "",
    val foto: String = "",
    val id: Int = 0,
    var nome: String = "",
    var rg: String = "",
    val senha: String = "",
    var telefone: String = "",
    val status_usuario: Int = 1
){
    override fun toString(): String {
        return "User(id=$id, 'cep=$cep', 'cpf=$cpf', 'data_nascimento=$data_nascimento', 'email=$email', 'foto=$foto','nome=$nome', 'rg=$rg', 'senha=$senha', 'telefone=$telefone', status_usuario = $status_usuario)"
    }
}