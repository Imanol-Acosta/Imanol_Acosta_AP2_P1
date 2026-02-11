package ucne.edu.imanol_acosta_ap2_p1.presentation.Navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object BorrameList : Screen()

    @Serializable
    data class Borrame(val borrameId: Int) : Screen()
}
