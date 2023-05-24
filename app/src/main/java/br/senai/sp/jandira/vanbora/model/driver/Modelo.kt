package br.senai.sp.jandira.vanbora.model.driver

data class Modelo(
    val id: Int = 0,
    val modelo: String = "",
    val status_modelo: Int = 0
){
    override fun toString(): String {
        return "Modelo(id=$id, 'modelo=$modelo', 'status_modelo=$status_modelo'"
    }
}