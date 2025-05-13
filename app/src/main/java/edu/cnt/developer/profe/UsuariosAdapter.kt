package edu.cnt.developer.profe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Esta clase proveerá las filas/datos al Recycler
 */

class UsuariosAdapter (var listaUsarios: List<Usuario>) : RecyclerView.Adapter<UsuariosViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuariosViewHolder {
        var usuarioViewHolder: UsuariosViewHolder

            var layoutInflater = LayoutInflater.from(parent.context)
            var filaUsuario =  layoutInflater.inflate(R.layout.fila_usuario, parent, false)
            usuarioViewHolder = UsuariosViewHolder(filaUsuario)

        return usuarioViewHolder
    }

    override fun onBindViewHolder(holder: UsuariosViewHolder, position: Int) {
        var usuario = listaUsarios.get(position)
        holder.rellenarUsuarioViewHolder(usuario)
    }

    override fun getItemCount(): Int {
        return this.listaUsarios.size
    }
}