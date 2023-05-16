package br.senai.sp.jandira.vanbora.components.headers.Rotas
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class DestinosDriver(
    val ico: ImageVector,
    val title: String,
    val route: String
){
    object RotaSuasVans: DestinosDriver(Icons.Filled.AirportShuttle, "Suas Vans", "Vizualizar suas vans")
    object RotaSeusContratos: DestinosDriver(Icons.Filled.Newspaper, "Contratos", "Vizualizar meus contratos")
    object RotaSuasEscolas: DestinosDriver(Icons.Filled.School, "Suas Escolas", "Suas escolas")
    object RotaNotificacoes: DestinosDriver(Icons.Filled.Notifications, "Notificações", "Notificações")
}
