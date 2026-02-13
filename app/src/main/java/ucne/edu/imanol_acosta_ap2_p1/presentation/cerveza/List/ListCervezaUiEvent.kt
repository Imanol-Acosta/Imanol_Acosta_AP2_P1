package ucne.edu.imanol_acosta_ap2_p1.presentation.cerveza.List

sealed interface ListCervezaUiEvent {
    data object Load : ListCervezaUiEvent
    data object Refresh : ListCervezaUiEvent
    data class Delete(val id: Int) : ListCervezaUiEvent
    data object CreateNew : ListCervezaUiEvent
    data class Edit(val id: Int) : ListCervezaUiEvent
    data class ShowMessage(val message: String) : ListCervezaUiEvent
    data object ClearMessage : ListCervezaUiEvent
}
