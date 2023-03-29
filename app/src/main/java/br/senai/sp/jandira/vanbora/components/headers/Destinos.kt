package br.senai.sp.jandira.vanbora.components.headers


import br.senai.sp.jandira.vanbora.R

sealed class Destinos(
    val ico: Int,
    val title: String,
    val rota: String
){
    object Rota1: Destinos(R.drawable.visibility, "Ver perfil", "Vizualizar seu perfil")
    object Rota2: Destinos(R.drawable.visibilityoff, "Meus contratos", "Vizualizar meus contratos")
    object Rota3: Destinos(R.drawable.visibility, "Localize-se", "Localize-se")
    object Rota4: Destinos(R.drawable.visibilityoff, "Contate-nos", "Contate-nos")
}