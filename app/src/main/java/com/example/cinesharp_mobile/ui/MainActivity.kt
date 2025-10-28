package com.example.cinesharp_mobile.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.lifecycle.lifecycleScope
import com.example.cinesharp_mobile.R
import com.example.cinesharp_mobile.dtos.CredencialesLoginDTO
import com.example.cinesharp_mobile.retrofit.RetrofitClient
import com.example.cinesharp_mobile.servicios.AuthService
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val loginService = RetrofitClient.instance.create(AuthService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            lifecycleScope.launch {
                try {
                    val response = loginService.login(CredencialesLoginDTO(email, password))
                    Log.d("Respuesta", response.toString())
                    if (response.token.token != null) {
                        val intent = Intent(this@MainActivity, CarteleraActivity::class.java)
                        startActivity(intent)
                        Toast.makeText(this@MainActivity, "Bienvenido a CineSharp 🎬", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@MainActivity, response.token.mensaje, Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Log.d("Excepcion", e.message.toString())
                    Toast.makeText(this@MainActivity, "Error de conexión", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}