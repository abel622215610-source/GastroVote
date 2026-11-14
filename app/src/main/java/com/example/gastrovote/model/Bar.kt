package com.example.gastrovote.model

data class Bar(

    //Campos de tapa
    val id: Int,
    val nombreBar: String,
    val nombreTapa: String,
    val descripcionTapa: String,
    val precioBase: Double,
    val puntuacionMediaTapa: Float,
    var esFavorito: Boolean = false,
    var puntuacionMedia: Int = 4,

    //Campos de reseñas
    var tamanoSeleccionado: String = "Tapa",
    var puntuacionEstrellas: Int = 0,
    var calidadPrecio: Float = 5f,
    var esVegetariana: Boolean = false,
    var esSinGluten: Boolean = false,
    var esPicante: Boolean = false,
    var meGusta: Boolean = false,
    var descuentoEstudiante: Boolean = false,
    var comentario: String = "",
    var resumenResenaTexto: String = "",
    var estaReseniado: Boolean = false
)