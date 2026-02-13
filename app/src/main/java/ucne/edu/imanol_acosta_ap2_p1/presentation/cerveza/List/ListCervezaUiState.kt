package ucne.edu.imanol_acosta_ap2_p1.presentation.cerveza.List

import ucne.edu.imanol_acosta_ap2_p1.domain.Model.Cerveza

data class ListCervezaUiState(
    val isLoading: Boolean = false,
    val cervezas: List<Cerveza> = emptyList(),
    val message: String? = null,
    val navigateToCreate: Boolean = false,
    val navigateToEditId: Int? = null,
    val totalCervezas: Int = 0,
    val promedioPuntuacion: Double = 0.0
)
