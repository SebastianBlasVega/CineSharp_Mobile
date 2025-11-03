package com.example.cinesharp_mobile.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cinesharp_mobile.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class PerfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        val btnCerrarSesion = findViewById<Button>(R.id.btnCerrarSesion)

        btnCerrarSesion.setOnClickListener {
            Toast.makeText(this, "Sesión cerrada correctamente", Toast.LENGTH_SHORT).show()

            // Redirigir a LoginActivity (o pantalla principal)
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, CarteleraActivity::class.java))
                    true
                }
                R.id.nav_favoritos -> {
                    startActivity(Intent(this, FavoritosActivity::class.java))
                    true
                }
                R.id.nav_perfil -> {
                    true
                }
                else -> false
            }
        }
    }
}
