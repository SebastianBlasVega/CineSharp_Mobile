package com.example.cinesharp_mobile.dtos


data class PeliculaDTO(
    val id: Int,
    val titulo: String,
    val imagen: String,
    val clasificacion: String,
    val duracionMinutos: Int,
    val idioma: String
)

data class DetallePeliculaDTO(
    val id: Int,
    val titulo: String,
    val imagen: String,
    val clasificacion: String,
    val duracionMinutos: Int,
    val idioma: String,
    val sinopsis: String,
    val funciones: List<PeliculaFuncion>
)


data class PeliculaFuncion(
    val fecha: String,
    val hora: String,
    val precio: Double,
    val sala: String
)