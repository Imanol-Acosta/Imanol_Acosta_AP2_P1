package ucne.edu.imanol_acosta_ap2_p1.presentation.cerveza.Edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ucne.edu.imanol_acosta_ap2_p1.domain.Model.Cerveza
import ucne.edu.imanol_acosta_ap2_p1.domain.Usecase.DeleteCervezaUseCase
import ucne.edu.imanol_acosta_ap2_p1.domain.Usecase.GetCervezaUseCase
import ucne.edu.imanol_acosta_ap2_p1.domain.Usecase.UpsertCervezaUseCase
import javax.inject.Inject

import androidx.navigation.toRoute
import ucne.edu.imanol_acosta_ap2_p1.presentation.navigation.Screen

@HiltViewModel
class EditCervezaViewModel @Inject constructor(
    private val getCervezaUseCase: GetCervezaUseCase,
    private val upsertCervezaUseCase: UpsertCervezaUseCase,
    private val deleteCervezaUseCase: DeleteCervezaUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val cervezaId: Int = savedStateHandle.toRoute<Screen.Cerveza>().cervezaId

    private val _state = MutableStateFlow(EditCervezaUiState())
    val state: StateFlow<EditCervezaUiState> = _state.asStateFlow()

    init {
        cervezaId?.let {
            if (it > 0) {
                loadCerveza(it)
            }
        }
    }

    fun onEvent(event: EditCervezaUiEvent) {
        when (event) {
            is EditCervezaUiEvent.Load -> loadCerveza(event.id)
            is EditCervezaUiEvent.NombreChanged -> _state.update {
                it.copy(nombre = event.value, nombreError = null)
            }
            is EditCervezaUiEvent.MarcaChanged -> _state.update {
                it.copy(marca = event.value, marcaError = null)
            }
            is EditCervezaUiEvent.PuntuacionChanged -> {
                val puntuacion = event.value.toIntOrNull()
                _state.update { it.copy(puntuacion = puntuacion, puntuacionError = null) }
            }
            EditCervezaUiEvent.Save -> onSave()
            EditCervezaUiEvent.Delete -> onDelete()
        }
    }

    private fun loadCerveza(id: Int) {
        viewModelScope.launch {
            val cerveza = getCervezaUseCase(id)
            if (cerveza != null) {
                _state.update {
                    it.copy(
                        isNew = false,
                        idCerveza = cerveza.idCerveza,
                        nombre = cerveza.nombre,
                        marca = cerveza.marca,
                        puntuacion = cerveza.puntuacion
                    )
                }
            } else {
                 _state.update { it.copy(isNew = true, idCerveza = null) }
            }
        }
    }

    private fun onSave() {
        if (state.value.isSaving) return

        if (state.value.nombre.isBlank()) {
            _state.update { it.copy(nombreError = "El nombre es requerido") }
            return
        }
        if (state.value.marca.isBlank()) {
            _state.update { it.copy(marcaError = "La marca es requerida") }
            return
        }
        if (state.value.puntuacion == null || state.value.puntuacion!! < 0) {
             _state.update { it.copy(puntuacionError = "Puntuación inválida") }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isSaving = true) }
            val cerveza = Cerveza(
                idCerveza = state.value.idCerveza ?: 0,
                nombre = state.value.nombre,
                marca = state.value.marca,
                puntuacion = state.value.puntuacion ?: 0
            )
            val result = upsertCervezaUseCase(cerveza)
            result.onSuccess {
                _state.update { it.copy(isSaving = false, saved = true) }
            }.onFailure { exception ->
                _state.update { it.copy(isSaving = false, message = exception.message) }
            }
        }
    }

    private fun onDelete() {
        val id = state.value.idCerveza ?: return
        viewModelScope.launch {
            _state.update { it.copy(isDeleting = true) }
            try {
                deleteCervezaUseCase(id)
                _state.update { it.copy(isDeleting = false, deleted = true) }
            } catch (e: Exception) {
                _state.update { it.copy(isDeleting = false, message = e.message) }
            }
        }
    }
}
