package br.senai.sp.jandira.vanbora.model.driver

data class Price(
    val id: Int = 0,
    val faixa_preco: String = ""
){
    override fun toString(): String {
        return "Preço(id=$id, 'faixa_preco=$faixa_preco',"
    }
}
