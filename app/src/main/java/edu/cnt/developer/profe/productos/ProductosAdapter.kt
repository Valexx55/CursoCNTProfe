package edu.cnt.developer.profe.productos

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.cnt.developer.profe.R
import edu.cnt.developer.profe.productos.ListadoProductosItem

class ProductosAdapter(var listaProductos: MutableList<ListadoProductosItem>) : RecyclerView.Adapter<ProductosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductosViewHolder {
        var productosViewHolder: ProductosViewHolder

        var layoutInflater = LayoutInflater.from(parent.context)
        var filaProducto = layoutInflater.inflate(R.layout.fila_producto, parent, false)

        productosViewHolder = ProductosViewHolder(filaProducto)

        return productosViewHolder
    }

    override fun getItemCount(): Int {
        return this.listaProductos.size
    }

    override fun onBindViewHolder(holder: ProductosViewHolder, position: Int) {
        var producto = this.listaProductos.get(position)
        this.listaProductos
        holder.rellenarProductosViewHolder(producto)
        holder.itemView.tag = position//TODO usad el id como tag en vez la posición
        holder.itemView.setOnClickListener { fila ->
            Log.d("MIAPP", "FILA TOCADA ${fila.tag}")
            this@ProductosAdapter.listaProductos.removeAt(fila.tag as Int)//elimino de la lista
            //this.notifyDataSetChanged()
            this.notifyItemRemoved(fila.tag as Int)//elimino de la vista
            //TODO pintar un menú contextual
            this.printCollectionType(this.listaProductos)

        }
    }

    fun printCollectionType(c: Collection<*>) {//wildcard *
        when (c) {
            is MutableList -> Log.d("MIAPP", "Es una MutableList")
            is List -> Log.d("MIAPP", "Es una List (inmutable)")
            is MutableSet -> Log.d("MIAPP", "Es un MutableSet")
            is Set -> Log.d("MIAPP", "Es un Set (inmutable)")
            is MutableMap<*, *> -> Log.d("MIAPP", "Es un MutableMap")
            is Map<*, *> -> Log.d("MIAPP", "Es un Map (inmutable)")
            else -> Log.d("MIAPP", "Otro tipo de colección")
        }
    }
}
