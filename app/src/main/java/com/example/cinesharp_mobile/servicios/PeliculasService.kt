package com.example.cinesharp_mobile.servicios

import com.example.cinesharp_mobile.dtos.PeliculaDTO
import retrofit2.Call
import retrofit2.http.GET

interface PeliculasService {
    @GET("peliculas/obtener-todas")
    fun obtenerPeliculas(): Call<MutableList<PeliculaDTO>>
}