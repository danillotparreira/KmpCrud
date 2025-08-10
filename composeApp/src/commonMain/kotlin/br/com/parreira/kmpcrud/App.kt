package br.com.parreira.kmpcrud

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import br.com.parreira.kmpcrud.core.di.appModule
import br.com.parreira.kmpcrud.features.post.view.PostScreen
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