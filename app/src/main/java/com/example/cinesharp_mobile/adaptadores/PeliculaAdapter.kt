package com.example.cinesharp_mobile.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cinesharp_mobile.R
import com.example.cinesharp_mobile.dtos.PeliculaDTO

class PeliculaAdapter(
    private val peliculas: List<PeliculaDTO>,
    private val onItemClick: (PeliculaDTO) -> Unit
) : RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder>() {

    class PeliculaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.imgPelicula)
        val titulo: TextView = itemView.findViewById(R.id.txtTitulo)
        val clasificacion: TextView = itemView.findViewById(R.id.txtClasificacion)
        val duracion: TextView = itemView.findViewById(R.id.txtDuracion)
        val imgEstrella: ImageView = itemView.findViewById(R.id.imgEstrella)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pelicula, parent, false)
        return PeliculaViewHolder(view)
    }

    override fun onBindViewHolder(holder: PeliculaViewHolder, position: Int) {
        val pelicula = peliculas[position]

        holder.titulo.text = pelicula.titulo
        holder.clasificacion.text = pelicula.clasificacion
        holder.duracion.text = "${pelicula.duracionMinutos} min"

        Glide.with(holder.itemView)
            .load(pelicula.imagen)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.img)

        // ⭐ Mostrar estrella solo si cumple cierta condición
        val peliculasFavoritas = listOf("Inception", "Interstellar")

        if (pelicula.titulo in peliculasFavoritas) {
            holder.imgEstrella.visibility = View.VISIBLE
        } else {
            holder.imgEstrella.visibility = View.GONE
        }
        
        holder.itemView.setOnClickListener {
            onItemClick(pelicula)
        }


    }

    override fun getItemCount() = peliculas.size
}
