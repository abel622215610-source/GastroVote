package com.example.gastrovote.model

data class Bar(
    val id: Int,
    val nombreBar: String,
    val nombreTapa: String,
    val descripcionTapa: String,
    val precioBase: Double,
    val puntuacionMediaTapa: Float,
    var esFavorito: Boolean = false
)