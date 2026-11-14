package com.example.gastrovote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gastrovote.ui.theme.GastroVoteTheme
import com.example.gastrovote.ui.viewmodel.GastroViewModel
import com.example.gastrovote.ui.components.*


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
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
        ) {
            item {
                Spacer(modifier = Modifier.height(23.dp))

                // 1. Cabecera
                CabeceraComponent()

                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                // 2. Selector de Bares
                SelectorBarComponent(
                    nombreBarActual = viewModel.barActual.nombreBar,
                    indiceActual = viewModel.indiceBarActual,
                    totalBares = viewModel.listaBares.size,
                    onAnteriorClick = { viewModel.anteriorBar() },
                    onSiguienteClick = { viewModel.siguienteBar() }
                )

                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                FichaBarComponent(
                    bar = viewModel.barActual,
                    onFavoritoClick = { viewModel.alternarFavoritoBarActual() }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                // 3. Formulario de reseña
                FormularioComponent(
                    tamanoSeleccionado = viewModel.tamanoSeleccionado,
                    onTamanoChange = { viewModel.tamanoSeleccionado = it },
                    puntuacionEstrellas = viewModel.puntuacionEstrellas,
                    onEstrellasChange = { viewModel.puntuacionEstrellas = it },
                    calidadPrecio = viewModel.calidadPrecio,
                    onCalidadPrecioChange = { viewModel.calidadPrecio = it },
                    esVegetariana = viewModel.esVegetariana,
                    onVegetarianaChange = { viewModel.esVegetariana = it },
                    esSinGluten = viewModel.esSinGluten,
                    onSinGlutenChange = { viewModel.esSinGluten = it },
                    esPicante = viewModel.esPicante,
                    onPicanteChange = { viewModel.esPicante = it },
                    meGusta = viewModel.meGusta,
                    onMeGustaChange = { viewModel.meGusta = it },
                    descuentoEstudiante = viewModel.descuentoEstudiante,
                    onDescuentoChange = { viewModel.descuentoEstudiante = it },
                    comentario = viewModel.comentario,
                    onComentarioChange = { viewModel.comentario = it },
                    onLimpiarClick = { viewModel.limpiarFormularioCompleto() },
                    mostrarDialogoLimpiar = viewModel.mostrarDialogoLimpiar,
                    onMostrarDialogoChange = { viewModel.mostrarDialogoLimpiarConfirmacion(it) },
                    onConfirmarLimpiar = { viewModel.confirmarLimpiarFormulario() },
                    onPublicarClick = { viewModel.publicarResena() },
                    resumenTexto = viewModel.resumenResenaTexto,
                    progresoBares = viewModel.progresoBaresReseniados,
                    totalBares = viewModel.listaBares.size,
                    reseniado = viewModel.barActualYaReseniado
                )
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    GastroVoteTheme {
        PantallaPrincipalGastroVote()
    }
}