package ucne.edu.imanol_acosta_ap2_p1.presentation.cerveza.List

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ucne.edu.imanol_acosta_ap2_p1.domain.Usecase.DeleteCervezaUseCase
import ucne.edu.imanol_acosta_ap2_p1.domain.Usecase.ObserveCervezaUseCase
import javax.inject.Inject

@HiltViewModel
class ListCervezaViewModel @Inject constructor(
    private val observeCervezaUseCase: ObserveCervezaUseCase,
    private val deleteCervezaUseCase: DeleteCervezaUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(ListCervezaUiState(isLoading = true))
    val state: StateFlow<ListCervezaUiState> = _state.asStateFlow()

    init {
        loadCervezas()
    }

    fun onEvent(event: ListCervezaUiEvent) {
        when (event) {
            ListCervezaUiEvent.Load -> loadCervezas()
            ListCervezaUiEvent.Refresh -> loadCervezas()
            is ListCervezaUiEvent.Delete -> onDelete(event.id)
            ListCervezaUiEvent.CreateNew -> _state.update { it.copy(navigateToCreate = true) }
            is ListCervezaUiEvent.Edit -> _state.update { it.copy(navigateToEditId = event.id) }
            is ListCervezaUiEvent.ShowMessage -> _state.update { it.copy(message = event.message) }
            ListCervezaUiEvent.ClearMessage -> _state.update { it.copy(message = null) }
        }
    }

    private fun loadCervezas() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            observeCervezaUseCase().collectLatest { cervezas ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        cervezas = cervezas,
                        totalCervezas = cervezas.size,
                        promedioPuntuacion = if (cervezas.isNotEmpty()) cervezas.map { it.puntuacion }.average() else 0.0,
                        message = null
                    )
                }
            }
        }
    }

    private fun onDelete(id: Int) {
        viewModelScope.launch {
            try {
                deleteCervezaUseCase(id)
                onEvent(ListCervezaUiEvent.ShowMessage("Cerveza eliminada"))
            } catch (e: Exception) {
                onEvent(ListCervezaUiEvent.ShowMessage("Error al eliminar cerveza"))
            }
        }
    }

    fun onNavigationHandled() {
        _state.update {
            it.copy(
                navigateToCreate = false,
                navigateToEditId = null,
                message = null
            )
        }
    }
}
