package br.senai.sp.jandira.vanbora.model.driver

data class Van(
    val foto: String = "",
    val id: Int = 0,
    val id_modelo: Int = 0,
    val id_motorista: Int = 0,
    val modelo: List<Modelo>,
    val placa: String = "",
    val quantidade_vagas: Int = 0,
    val status_van: Int = 0
){
    override fun toString(): String {
        return "Van(id=$id, 'modelo=$modelo', 'foto=$foto', 'id_modelo=$id_modelo', 'id_motorista=$id_motorista', 'placa=$placa', 'quantidade_vagas=$quantidade_vagas', 'status_van=$status_van', "
    }
}