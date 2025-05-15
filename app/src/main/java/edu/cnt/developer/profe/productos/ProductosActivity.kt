package edu.cnt.developer.profe.productos

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.cnt.developer.profe.R
import edu.cnt.developer.profe.RedUtil
import edu.cnt.developer.profe.databinding.ActivityProductosBinding
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductosActivity : AppCompatActivity() {

    lateinit var productoService: ProductoService
    lateinit var listadoProductos: ListadoProductos
    lateinit var binding: ActivityProductosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        productoService = retrofit.create(ProductoService::class.java)

        if (RedUtil.hayInternet(this))
        {
            Log.d("MIAPP", "Hay internet")
            //cargar los productos
            lifecycleScope.launch {
                Log.d("MIAPP", "En corrutina 1")
                listadoProductos = productoService.obtenerProductos()
                binding.barraProgreso.visibility = View.INVISIBLE//hacemos que la barra de progreso se haga invisible después de conseguir los productos
                Log.d("MIAPP", "En corrutina 2 TAMAÑO ${listadoProductos.size}")
                listadoProductos.forEach{
                    producto -> Log.d("MIAPP", "PRODUCTO $producto")
                }
                //this@ProductosActivity.mostrarListaProductos()
                //ProductosActivity.this

                mostrarListaProductos()
            }
            Log.d("MIAPP", "Fuera de corrutina")


        } else {
            //TOAST informativo
            binding.barraProgreso.visibility = View.INVISIBLE//hacemos que la barra de progreso se haga invisible después de conseguir los productos
            Toast.makeText(this, "SIN ACCESO A INTERNET", Toast.LENGTH_LONG).show()
        }

    }

    fun mostrarListaProductos ()
    {
        //TODO: ARMAR EL RECYCLER, EL ADAPTER, VIEWHOLDER Y EL LAYAOUT MANAGER, PARA MOSTRAR LA LISTA
        binding.rvlistaproductos.adapter = ProductosAdapter(listadoProductos)
        binding.rvlistaproductos.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

    }

}