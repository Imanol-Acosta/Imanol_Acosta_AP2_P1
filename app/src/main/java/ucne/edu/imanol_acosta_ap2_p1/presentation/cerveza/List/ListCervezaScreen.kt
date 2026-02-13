package ucne.edu.imanol_acosta_ap2_p1.presentation.cerveza.List

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ucne.edu.imanol_acosta_ap2_p1.domain.Model.Cerveza

@Composable
fun ListCervezaScreen(
    onDrawer: () -> Unit,
    goToCerveza: (Int) -> Unit,
    createCerveza: () -> Unit,
    viewModel: ListCervezaViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    
    LaunchedEffect(state.navigateToCreate) {
        if (state.navigateToCreate) {
            createCerveza()
            viewModel.onNavigationHandled()
        }
    }

    LaunchedEffect(state.navigateToEditId) {
        state.navigateToEditId?.let { id ->
            goToCerveza(id)
            viewModel.onNavigationHandled()
        }
    }
    
    ListCervezaBody(
        state = state,
        onDrawer = onDrawer,
        onEvent = viewModel::onEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ListCervezaBody(
    state: ListCervezaUiState,
    onDrawer: () -> Unit,
    onEvent: (ListCervezaUiEvent) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(state.message) {
        state.message?.let { message ->
            snackbarHostState.showSnackbar(message)
            onEvent(ListCervezaUiEvent.ClearMessage)
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Listado de Cervezas") },
                navigationIcon = {
                    IconButton(onClick = onDrawer) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onEvent(ListCervezaUiEvent.CreateNew) }) {
                Text("+")
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            if (state.cervezas.isEmpty() && !state.isLoading) {
                 Text(
                    text = "No hay cervezas agregadas",
                    modifier = Modifier.align(Alignment.Center),
                    style = MaterialTheme.typography.bodyLarge
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(state.cervezas) { cerveza ->
                        CervezaCard(
                            cerveza = cerveza,
                            onClick = { onEvent(ListCervezaUiEvent.Edit(cerveza.idCerveza)) },
                            onDelete = { onEvent(ListCervezaUiEvent.Delete(cerveza.idCerveza)) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CervezaCard(
    cerveza: Cerveza,
    onClick: () -> Unit,
    onDelete: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(cerveza.nombre, style = MaterialTheme.typography.titleMedium)
                Text(cerveza.marca, style = MaterialTheme.typography.bodyMedium)
                Text("Puntuación: ${cerveza.puntuacion}", style = MaterialTheme.typography.bodySmall)
            }
            IconButton(onClick = { onDelete(cerveza.idCerveza) }) {
                Icon(Icons.Default.Delete, contentDescription = "Eliminar")
            }
        }
    }
}
