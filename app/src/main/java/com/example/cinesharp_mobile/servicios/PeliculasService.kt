package com.example.cinesharp_mobile.servicios

import com.example.cinesharp_mobile.dtos.DetallePeliculaDTO
import com.example.cinesharp_mobile.dtos.PeliculaDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PeliculasService {
    @GET("peliculas/obtener-todas")
    fun obtenerPeliculas(): Call<MutableList<PeliculaDTO>>

    @GET("peliculas/detalle/{id}")
    fun obtenerPeliculaPorId(@Path("id") id: Int): Call<DetallePeliculaDTO>

}