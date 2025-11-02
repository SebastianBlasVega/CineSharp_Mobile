package com.example.cinesharp_mobile.ui

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.cinesharp_mobile.R
import com.example.cinesharp_mobile.dtos.DetallePeliculaDTO
import com.example.cinesharp_mobile.retrofit.RetrofitClient
import com.example.cinesharp_mobile.servicios.PeliculasService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetallesActivity : AppCompatActivity() {

    private val peliculasService = RetrofitClient.instance.create(PeliculasService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalles)

        val peliculaId = intent.getIntExtra("pelicula_id", -1)

        if (peliculaId != -1) {
            obtenerDetallesPelicula(peliculaId)
        } else {
            Toast.makeText(this, "Error: ID no recibido", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun obtenerDetallesPelicula(id: Int) {
        peliculasService.obtenerPeliculaPorId(id).enqueue(object : Callback<DetallePeliculaDTO> {
            override fun onResponse(call: Call<DetallePeliculaDTO>, response: Response<DetallePeliculaDTO>) {
                if (response.isSuccessful) {
                    response.body()?.let { mostrarDetalles(it) }
                } else {
                    Toast.makeText(this@DetallesActivity, "Error al cargar detalles", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<DetallePeliculaDTO>, t: Throwable) {
                Toast.makeText(this@DetallesActivity, "Fallo: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun mostrarDetalles(pelicula: DetallePeliculaDTO) {
        val img = findViewById<ImageView>(R.id.imgPeliculaDetalle)
        val titulo = findViewById<TextView>(R.id.txtTituloDetalle)
        val clasificacion = findViewById<TextView>(R.id.txtClasificacionDetalle)
        val duracion = findViewById<TextView>(R.id.txtDuracionDetalle)
        val sinopsis = findViewById<TextView>(R.id.txtSinopsisDetalle)
        val contenedorFunciones = findViewById<LinearLayout>(R.id.contenedorFunciones)

        titulo.text = pelicula.titulo
        clasificacion.text = pelicula.clasificacion
        duracion.text = "${pelicula.duracionMinutos} min"
        sinopsis.text = pelicula.sinopsis

        Glide.with(this)
            .load(pelicula.imagen)
            .placeholder(R.drawable.ic_launcher_background)
            .into(img)

        // Mostrar lista de funciones
        contenedorFunciones.removeAllViews()
        pelicula.funciones.forEach { funcion ->
            val textoFuncion = TextView(this).apply {
                text = "📅 ${funcion.fecha} - 🕒 ${funcion.hora} - 💰 S/ ${funcion.precio} - Sala ${funcion.sala}"
                textSize = 14f
                setPadding(0, 8, 0, 8)
                setTextColor(Color.WHITE)
            }
            contenedorFunciones.addView(textoFuncion)
        }
    }
}
