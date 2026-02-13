package ucne.edu.imanol_acosta_ap2_p1.presentation.cerveza.Edit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditCervezaScreen(
    onNavigateBack: () -> Unit,
    viewModel: EditCervezaViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.saved, state.deleted) {
        if (state.saved || state.deleted) {
            onNavigateBack()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (state.isNew) "Nueva Cerveza" else "Editar Cerveza") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { padding ->
        EditCervezaBody(
            state = state,
            onEvent = viewModel::onEvent,
            modifier = Modifier.padding(padding)
        )
    }
}

@Composable
private fun EditCervezaBody(
    state: EditCervezaUiState,
    onEvent: (EditCervezaUiEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        OutlinedTextField(
            value = state.nombre,
            onValueChange = { onEvent(EditCervezaUiEvent.NombreChanged(it)) },
            label = { Text("Nombre") },
            isError = state.nombreError != null,
            modifier = Modifier.fillMaxWidth()
        )
        if (state.nombreError != null) {
            Text(
                text = state.nombreError,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = state.marca,
            onValueChange = { onEvent(EditCervezaUiEvent.MarcaChanged(it)) },
            label = { Text("Marca") },
            isError = state.marcaError != null,
            modifier = Modifier.fillMaxWidth()
        )
        if (state.marcaError != null) {
            Text(
                text = state.marcaError,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = state.puntuacion?.toString() ?: "",
            onValueChange = { onEvent(EditCervezaUiEvent.PuntuacionChanged(it)) },
            label = { Text("Puntuación") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = state.puntuacionError != null,
            modifier = Modifier.fillMaxWidth()
        )
        if (state.puntuacionError != null) {
            Text(
                text = state.puntuacionError,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = { onEvent(EditCervezaUiEvent.Save) },
                enabled = !state.isSaving,
                modifier = Modifier.weight(1f)
            ) {
                Text("Guardar")
            }

            if (!state.isNew) {
                Spacer(Modifier.width(8.dp))
                OutlinedButton(
                    onClick = { onEvent(EditCervezaUiEvent.Delete) },
                    enabled = !state.isDeleting,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Eliminar")
                }
            }
        }
    }
}
