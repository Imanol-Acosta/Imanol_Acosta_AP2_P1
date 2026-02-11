package ucne.edu.imanol_acosta_ap2_p1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ucne.edu.imanol_acosta_ap2_p1.presentation.Navigation.BorrameNavHost
import ucne.edu.imanol_acosta_ap2_p1.ui.theme.Imanol_Acosta_AP2_P1Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Imanol_Acosta_AP2_P1Theme {
                val navController = rememberNavController()
                BorrameNavHost(navController)
            }
        }
    }
}