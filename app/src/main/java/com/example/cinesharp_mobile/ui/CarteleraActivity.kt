package com.example.cinesharp_mobile.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinesharp_mobile.R
import com.example.cinesharp_mobile.dtos.PeliculaDTO
import com.example.cinesharp_mobile.retrofit.RetrofitClient
import com.example.cinesharp_mobile.servicios.PeliculasService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CarteleraActivity : AppCompatActivity() {

    private val peliculasService = RetrofitClient.instance.create(PeliculasService::class.java)
    private lateinit var recyclerPeliculas: RecyclerView
    private lateinit var adapter: PeliculaAdapter
    private var listaPeliculas: MutableList<PeliculaDTO> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cartelera_activity)

        recyclerPeliculas = findViewById(R.id.recyclerPeliculas)
        recyclerPeliculas.layoutManager = LinearLayoutManager(this)

        adapter = PeliculaAdapter(listaPeliculas) { peliculaSeleccionada ->
            val intent = Intent(this, DetallesActivity::class.java)
            intent.putExtra("pelicula_id", peliculaSeleccionada.id)
            startActivity(intent)
        }

        recyclerPeliculas.adapter = adapter

        cargarPeliculas()
    }

    private fun cargarPeliculas() {
        peliculasService.obtenerPeliculas().enqueue(object : Callback<MutableList<PeliculaDTO>> {
            override fun onResponse(
                call: Call<MutableList<PeliculaDTO>>,
                response: Response<MutableList<PeliculaDTO>>
            ) {
                if (response.isSuccessful) {
                    listaPeliculas.clear()
                    listaPeliculas.addAll(response.body() ?: mutableListOf())
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(this@CarteleraActivity, "Error al cargar películas", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MutableList<PeliculaDTO>>, t: Throwable) {
                Toast.makeText(this@CarteleraActivity, "Fallo: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
