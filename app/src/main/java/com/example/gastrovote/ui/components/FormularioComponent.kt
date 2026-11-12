package com.example.gastrovote.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun FormularioComponent(
    // Estados recibidos del ViewModel
    tamanoSeleccionado: String,
    onTamanoChange: (String) -> Unit,
    puntuacionEstrellas: Int,
    onEstrellasChange: (Int) -> Unit,
    calidadPrecio: Float,
    onCalidadPrecioChange: (Float) -> Unit,
    esVegetariana: Boolean,
    onVegetarianaChange: (Boolean) -> Unit,
    esSinGluten: Boolean,
    onSinGlutenChange: (Boolean) -> Unit,
    esPicante: Boolean,
    onPicanteChange: (Boolean) -> Unit,
    meGusta: Boolean,
    onMeGustaChange: (Boolean) -> Unit,
    descuentoEstudiante: Boolean,
    onDescuentoChange: (Boolean) -> Unit,
    comentario: String,
    onComentarioChange: (String) -> Unit,
    onLimpiarClick: () -> Unit,
    onPublicarClick: () -> Unit,
    resumenTexto: String,
    progresoBares: Int,
    totalBares: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 8.dp)
    ) {

        // TÍTULO SECCIÓN "TU RESEÑA"
        Text(
            text = "TU RESEÑA",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(8.dp))

        // TAMAÑO (RadioButtons: Tapa, Media, Ración)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "Tamaño", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)

            listOf("Tapa", "Media", "Ración").forEach { opcion ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = tamanoSeleccionado == opcion,
                        onClick = { onTamanoChange(opcion) }
                    )
                    Text(text = opcion, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // PUNTUACIÓN (Estrellas interactivas y texto dinámico)
        Text(text = "Puntuación", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                repeat(5) { index ->
                    val estrellaNum = index + 1
                    IconButton(
                        onClick = { onEstrellasChange(estrellaNum) },
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = if (estrellaNum <= puntuacionEstrellas) Icons.Default.Star else Icons.Default.StarBorder,
                            contentDescription = "Estrella $estrellaNum",
                            tint = Color(0xFFFFC107),
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
            }

            val textoOpinion = when (puntuacionEstrellas) {
                1 -> "Terrible"
                2 -> "Aceptable"
                3 -> "Bueno"
                4 -> "Muy bueno"
                5 -> "¡Espectacular!"
                else -> ""
            }
            Text(
                text = textoOpinion,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // CALIDAD-PRECIO (Slider 0 a 10)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Calidad-precio", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
            Text(text = "${calidadPrecio.toInt()}/10", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
        }
        Slider(
            value = calidadPrecio,
            onValueChange = onCalidadPrecioChange,
            valueRange = 0f..10f,
            steps = 9,
            colors = SliderDefaults.colors(thumbColor = Color(0xFFb23a1e), activeTrackColor = Color(0xFFb23a1e))
        )

        Spacer(modifier = Modifier.height(16.dp))

        // CHECKBOXES (Alérgenos y tipos)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = esVegetariana, onCheckedChange = onVegetarianaChange)
                Text(text = "Vegetariana", style = MaterialTheme.typography.bodySmall)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = esSinGluten, onCheckedChange = onSinGlutenChange)
                Text(text = "Sin gluten", style = MaterialTheme.typography.bodySmall)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = esPicante, onCheckedChange = onPicanteChange)
                Text(text = "Picante", style = MaterialTheme.typography.bodySmall)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // SWITCH "ME GUSTA"
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Me gusta", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(8.dp))
                Switch(
                    checked = meGusta,
                    onCheckedChange = onMeGustaChange,
                    colors = SwitchDefaults.colors(checkedThumbColor = Color(0xFFb23a1e), checkedTrackColor = Color(0xFFb23a1e).copy(alpha = 0.5f))
                )
            }
            Text(
                text = if (meGusta) "¡Te gusta esta tapa!" else "",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // DESCUENTO ESTUDIANTE
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = descuentoEstudiante, onCheckedChange = onDescuentoChange)
            Text(text = "Descuento estudiante (10%)", style = MaterialTheme.typography.bodyMedium)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // CAMPO DE TEXTO (COMENTARIO TAPA)
        OutlinedTextField(
            value = comentario,
            onValueChange = onComentarioChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Muy buena, repetiría la próxima semana") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
        )

        // BOTONES: LIMPIAR / PUBLICAR RESEÑA
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = onLimpiarClick,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFd8cbe8))
            ) {
                Text(text = "LIMPIAR", color = Color.Black)
            }

            Button(
                onClick = onPublicarClick,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFb23a1e))
            ) {
                Text(text = "PUBLICAR RESEÑA", color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // TARJETA DE RESULTADOS
        if (resumenTexto.isNotBlank()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = resumenTexto,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        // BARRA DE PROGRESO DE LA RUTA
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Ruta: $progresoBares de $totalBares bares",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        LinearProgressIndicator(
            progress = { if (totalBares > 0) progresoBares.toFloat() / totalBares.toFloat() else 0f },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp),
            color = Color(0xFFb23a1e),
            trackColor = Color.LightGray
        )
    }
}