package com.example.cinesharp_mobile.ui


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cartelera_activity)

        val recycler = findViewById<RecyclerView>(R.id.recyclerPeliculas)

        peliculasService.obtenerPeliculas().enqueue(object : Callback<MutableList<PeliculaDTO>> {
            override fun onResponse(
                call: Call<MutableList<PeliculaDTO>>,
                response: Response<MutableList<PeliculaDTO>>
            ) {
                if (response.isSuccessful) {
                    val peliculas = response.body() ?: mutableListOf()
                    recycler.adapter = PeliculaAdapter(peliculas)
                } else {
                    Toast.makeText(this@CarteleraActivity, "Error al cargar", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MutableList<PeliculaDTO>>, t: Throwable) {
                Toast.makeText(this@CarteleraActivity, "Fallo: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}