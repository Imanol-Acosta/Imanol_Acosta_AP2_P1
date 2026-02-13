package ucne.edu.imanol_acosta_ap2_p1.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object CervezaList : Screen()

    @Serializable
    data class Cerveza(val cervezaId: Int) : Screen()
}
