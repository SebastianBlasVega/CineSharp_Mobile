package com.example.cinesharp_mobile.servicios

import com.example.cinesharp_mobile.model.Peliculas
import retrofit2.Call
import retrofit2.http.GET

interface PeliculasServicios {
    @GET("peliculas/obtener-todas")
    fun obtenerPeliculas(): Call<MutableList<Peliculas>>
}