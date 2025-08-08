package br.com.parreira.kmpcrudsimples

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import br.com.parreira.kmpcrudsimples.di.appModule
import br.com.parreira.kmpcrudsimples.ui.PostScreen
import org.koin.compose.KoinApplication

@Composable
fun App() {
    KoinApplication(application = {
        modules(appModule)
    }) {
        MaterialTheme {
            PostScreen()
        }
    }
}