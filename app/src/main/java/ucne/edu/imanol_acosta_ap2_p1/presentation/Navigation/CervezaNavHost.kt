package ucne.edu.imanol_acosta_ap2_p1.presentation.Navigation

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.coroutines.launch
import ucne.edu.imanol_acosta_ap2_p1.presentation.cerveza.Edit.EditCervezaScreen
import ucne.edu.imanol_acosta_ap2_p1.presentation.cerveza.List.ListCervezaScreen
import ucne.edu.imanol_acosta_ap2_p1.presentation.navigation.Screen

@Composable
fun CervezaNavHost(
    navHostController: NavHostController
) {
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    DrawerMenu(
        drawerState = drawerState,
        navHostController = navHostController
    ) {
        NavHost(
            navController = navHostController,
            startDestination = Screen.CervezaList
        ) {
            composable<Screen.CervezaList> {
                ListCervezaScreen(
                    onDrawer = {
                        scope.launch {
                            drawerState.open()
                        }
                    },
                    createCerveza = {
                        navHostController.navigate(Screen.Cerveza(0))
                    },
                    goToCerveza = {
                        navHostController.navigate(Screen.Cerveza(it))
                    }
                )
            }

            composable<Screen.Cerveza> {
                val args = it.toRoute<Screen.Cerveza>()
                EditCervezaScreen(
                    onNavigateBack = {
                        navHostController.navigateUp()
                    }
                )
            }
        }
    }
}
