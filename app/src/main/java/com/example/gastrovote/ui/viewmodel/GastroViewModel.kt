package com.example.gastrovote.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableStateSetOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.gastrovote.model.Bar

class GastroViewModel : ViewModel() {

    // Lista de los 8 bares de la ruta (puedes adaptarla o cargarla de recursos)
    val listaBares = mutableStateListOf(
        Bar(0, "Taberna La Marinera", "Marinera", "Rosadilla con ensaladilla y anchoa", 2.50, 4.0f),
        Bar(1, "Bar El Rinconcito", "Croqueta Casera", "Croqueta cremosa de jamón ibérico", 1.80, 4.5f),
        Bar(2, "Rincón Murciano", "Pisto Huertano", "Pisto tradicional con huevo de codorniz", 3.00, 4.2f),
        Bar(3, "La Tapa d'Aki", "Marinerito", "Variante con boquerón en vinagre", 2.50, 3.8f),
        Bar(4, "Bar Central", "Caballitos", "Gambas en gabardina crujientes", 2.20, 4.6f),
        Bar(5, "Mesón El Sabor", "Pulpo a la Murciana", "Pulpo tierno al horno con pimentón", 4.50, 4.9f),
        Bar(6, "La Bodeguita", "Montadito de Lomo", "Lomo adobado con queso de cabra", 2.80, 4.1f),
        Bar(7, "El Último trago", "Paparajote", "Hoja de limón con masa frita y azúcar", 2.00, 4.7f)
    )

    // Índice del bar seleccionado actualmente (de 0 a 7)
    var indiceBarActual by mutableStateOf(0)
        private set

    // --- ESTADOS DEL FORMULARIO DE RESEÑA ---
    var tamanoSeleccionado by mutableStateOf("Tapa") // "Tapa", "Media ración", "Ración"
    var puntuacionEstrellas by mutableStateOf(0)     // 0 a 5
    var calidadPrecio by mutableStateOf(5f)          // 0 a 10
    var esVegetariana by mutableStateOf(false)
    var esSinGluten by mutableStateOf(false)
    var esPicante by mutableStateOf(false)
    var meGusta by mutableStateOf(false)
    var descuentoEstudiante by mutableStateOf(false)
    var comentario by mutableStateOf("")

    // --- ESTADOS DE RESULTADOS Y PROGRESO ---
    var resumenResenaTexto by mutableStateOf("")
        private set

    // Bares únicos reseñados para la barra de progreso (guarda los IDs)
    private val baresReseniadosSet = mutableStateSetOf<Int>()
    val progresoBaresReseniados: Int
        get() = baresReseniadosSet.size

    // Estado para controlar la visibilidad del diálogo de Limpiar
    var mostrarDialogoLimpiar by mutableStateOf(false)

    // Bar actual que se está visualizando
    val barActual: Bar
        get() = listaBares[indiceBarActual]

    // --- FUNCIONES DE NAVEGACIÓN (L1) ---
    fun siguienteBar() {
        indiceBarActual = (indiceBarActual + 1) % listaBares.size
    }

    fun anteriorBar() {
        indiceBarActual = if (indiceBarActual - 1 < 0) listaBares.size - 1 else indiceBarActual - 1
    }

    // --- FAVORITOS (L2) ---
    fun alternarFavoritoBarActual() {
        val bar = barActual
        bar.esFavorito = !bar.esFavorito
        // Forzar recomposición actualizando el elemento en la lista
        listaBares[indiceBarActual] = bar
    }

    // --- PUBLICAR RESEÑA (L6, L7) ---
    // Devuelve true si se publicó con éxito, false si faltaba puntuar
    fun publicarResena(): Boolean {
        if (puntuacionEstrellas == 0) {
            return false // Requiere validación de estrellas
        }

        // 1. Cálculo del precio según tamaño (Tapa x1, Media x2, Ración x3)
        val multiplicador = when (tamanoSeleccionado) {
            "Media ración" -> 2
            "Ración" -> 3
            else -> 1
        }
        val precioBaseTotal = barActual.precioBase * multiplicador

        // 2. Descuento de estudiante (10%)
        val descuento = if (descuentoEstudiante) precioBaseTotal * 0.10 else 0.0
        val precioFinal = precioBaseTotal - descuento

        // 3. Generar texto de opinión de estrellas
        val textoOpinion = when (puntuacionEstrellas) {
            1 -> "Mejorable"
            2 -> "Correcta"
            3 -> "¡Muy buena!"
            4, 5 -> "¡Espectacular!"
            else -> "Sin puntuar"
        }

        // 4. Construir resumen detallado
        resumenResenaTexto = """
            ${barActual.nombreBar} - ${barActual.nombreTapa}
            Tamaño: $tamanoSeleccionado - Puntuación: $puntuacionEstrellas/5 ($textoOpinion)
            Calidad-precio: ${calidadPrecio.toInt()}/10 | Me gusta: ${if (meGusta) "Sí" else "No"}
            Precio base: ${String.format("%.2f", precioBaseTotal)} €
            Descuento estudiante: -${String.format("%.2f", descuento)} €
            Precio final: ${String.format("%.2f", precioFinal)} €
            Comentario: ${if (comentario.isBlank()) "Sin comentarios" else comentario}
        """.trimIndent()

        // 5. Registrar en la ruta (bares distintos)
        baresReseniadosSet.add(barActual.id)

        return true
    }

    // --- LIMPIAR FORMULARIO (L8) ---
    fun limpiarFormularioCompleto() {
        tamanoSeleccionado = "Tapa"
        puntuacionEstrellas = 0
        calidadPrecio = 5f
        esVegetariana = false
        esSinGluten = false
        esPicante = false
        meGusta = false
        descuentoEstudiante = false
        comentario = ""
    }
}