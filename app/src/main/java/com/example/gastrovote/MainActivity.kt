package com.example.gastrovote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gastrovote.ui.components.CabeceraComponent
import com.example.gastrovote.ui.components.SelectorBarComponent
import com.example.gastrovote.ui.theme.GastroVoteTheme
import com.example.gastrovote.ui.viewmodel.GastroViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GastroVoteTheme {
                PantallaPrincipalGastroVote()
            }
        }
    }
}

@Composable
fun PantallaPrincipalGastroVote(
    viewModel: GastroViewModel = viewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // ¡Requisito de desplazamiento vertical![cite: 2, 4]
    ) {

        Spacer(modifier = Modifier.height(23.dp))
        // 1. Cabecera
        CabeceraComponent()

        Spacer(modifier = Modifier.height(16.dp))

        // 2. Selector de Bares (conectado a las funciones del ViewModel)[cite: 3, 4]
        SelectorBarComponent(
            nombreBarActual = viewModel.barActual.nombreBar,
            indiceActual = viewModel.indiceBarActual,
            totalBares = viewModel.listaBares.size,
            onAnteriorClick = { viewModel.anteriorBar() },
            onSiguienteClick = { viewModel.siguienteBar() }
        )

        // Aquí debajo irán después la Ficha del Bar, el Formulario, etc.
    }
}


@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    GastroVoteTheme {
        PantallaPrincipalGastroVote()
    }
}