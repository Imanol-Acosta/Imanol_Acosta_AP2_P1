package ucne.edu.imanol_acosta_ap2_p1.presentation.cerveza.Edit

data class EditCervezaUiState(
    val idCerveza: Int? = null,
    val nombre: String = "",
    val nombreError: String? = null,
    val marca: String = "",
    val marcaError: String? = null,
    val puntuacion: Int? = null,
    val puntuacionError: String? = null,
    val isSaving: Boolean = false,
    val saved: Boolean = false,
    val isDeleting: Boolean = false,
    val deleted: Boolean = false,
    val isNew: Boolean = true,
    val message: String? = null
)
