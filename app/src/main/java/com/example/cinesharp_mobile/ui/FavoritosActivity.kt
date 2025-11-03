package com.example.cinesharp_mobile.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinesharp_mobile.R
import com.example.cinesharp_mobile.dtos.PeliculaDTO
import com.google.android.material.bottomnavigation.BottomNavigationView

class FavoritosActivity : AppCompatActivity() {

    private lateinit var recyclerFavoritos: RecyclerView
    private lateinit var adapter: PeliculaAdapter
    private var listaFavoritos: MutableList<PeliculaDTO> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritos)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, CarteleraActivity::class.java))
                    true
                }
                R.id.nav_favoritos -> {

                    true
                }
                R.id.nav_perfil -> {
                    startActivity(Intent(this, PerfilActivity::class.java))
                    true
                }
                else -> false
            }
        }

        recyclerFavoritos = findViewById(R.id.recyclerFavoritos)
        recyclerFavoritos.layoutManager = LinearLayoutManager(this)

        adapter = PeliculaAdapter(listaFavoritos) { peliculaSeleccionada ->
            Toast.makeText(this, "Seleccionaste: ${peliculaSeleccionada.titulo}", Toast.LENGTH_SHORT).show()
        }

        recyclerFavoritos.adapter = adapter

        cargarFavoritos()
    }

    private fun cargarFavoritos() {
        listaFavoritos.add(
            PeliculaDTO(
                id = 1,
                titulo = "Interstellar",
                clasificacion = "PG-13",
                duracionMinutos = 169,
                imagen = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/nCbkOyOMTEwlEV0LtCOvCnwEONA.jpg",
                idioma = "Quechua"
            )
        )

        listaFavoritos.add(
            PeliculaDTO(
                id = 2,
                titulo = "Inception",
                clasificacion = "PG-13",
                duracionMinutos = 148,
                imagen = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/ljsZTbVsrQSqZgWeep2B1QiDKuh.jpg",
                idioma = "Quechua"
            )
        )

        adapter.notifyDataSetChanged()
    }
}
