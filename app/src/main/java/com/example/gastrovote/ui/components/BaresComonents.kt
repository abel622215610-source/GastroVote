package com.example.gastrovote.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.gastrovote.R // Asegúrate de importar tus recursos correctamente

// ==========================================
// A. CABECERA DE LA APP
// ==========================================
@Composable
fun CabeceraComponent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        // Banner superior (puedes cambiar la imagen en drawable por la tuya propia)
        // Nota: Asegúrate de tener un recurso de imagen en res/drawable, por ejemplo "banner_tapas"
        // o usa un Box con color/imagen de prueba.
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.banner_tapas),
                contentDescription = "Banner GastroVote",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(
                text = "GastroVote",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Ruta de la tapa",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

// ==========================================
// B. ELIGE EL BAR (SELECTOR CIRCULAR)
// ==========================================
@Composable
fun SelectorBarComponent(
    nombreBarActual: String,
    indiceActual: Int,
    totalBares: Int,
    onAnteriorClick: () -> Unit,
    onSiguienteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "ELIGE EL BAR",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Botón Bar Anterior (FilledTonalButton según tabla de equivalencias)
            FilledTonalIconButton(onClick = onAnteriorClick) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Bar anterior"
                )
            }

            // Nombre del bar y posición ("Bar 1 de 8")
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = nombreBarActual,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Bar ${indiceActual + 1} de $totalBares",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Botón Bar Siguiente
            FilledTonalIconButton(onClick = onSiguienteClick) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "Bar siguiente"
                )
            }
        }
    }
}