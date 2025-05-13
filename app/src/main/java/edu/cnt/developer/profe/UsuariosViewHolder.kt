package edu.cnt.developer.profe

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Esta clase representa la fila / la caja que se recicla y contiene la info de un Usuario
 * que se muestra
 *
 */
class UsuariosViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {

    val nombreUsuario: TextView = itemView.findViewById(R.id.nombreUsuario)
    val edadUsuario: TextView = itemView.findViewById(R.id.edadUsuario)

    fun rellenarUsuarioViewHolder (usuario: Usuario)
    {
        this.nombreUsuario.text = usuario.nombre
        this.edadUsuario.text = usuario.edad.toString()
    }

}