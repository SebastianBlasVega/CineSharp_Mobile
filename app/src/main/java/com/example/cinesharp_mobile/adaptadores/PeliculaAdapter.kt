
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

class PeliculaAdapter(private val peliculas: List<PeliculaDTO>) :
    RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder>() {

    class PeliculaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img = itemView.findViewById<ImageView>(R.id.imgPelicula)
        val titulo = itemView.findViewById<TextView>(R.id.txtTitulo)
        val clasificacion = itemView.findViewById<TextView>(R.id.txtClasificacion)
        val duracion = itemView.findViewById<TextView>(R.id.txtDuracion)
        val idioma = itemView.findViewById<TextView>(R.id.txtIdioma)
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
        holder.idioma.text = pelicula.idioma

        Glide.with(holder.itemView)
            .load(pelicula.imagen)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.img)
    }

    override fun getItemCount() = peliculas.size
}