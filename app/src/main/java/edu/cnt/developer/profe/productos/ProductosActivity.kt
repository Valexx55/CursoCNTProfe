package edu.cnt.developer.profe.productos

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import edu.cnt.developer.profe.R
import edu.cnt.developer.profe.RedUtil
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductosActivity : AppCompatActivity() {

    lateinit var productoService: ProductoService
    lateinit var listadoProductos: ListadoProductos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos)

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
                Log.d("MIAPP", "En corrutina 2 TAMAÑO ${listadoProductos.size}")
                listadoProductos.forEach{
                    producto -> Log.d("MIAPP", "PRODUCTO $producto")
                }
                //TODO: ARMAR EL RECYCLER, EL ADAPTER, EL LAYAOUT MANAGER, PARA MOSTRAR LA LISTA
                //PROGRESSBAR
                //FRAMELAYOUT
            }
            Log.d("MIAPP", "Fuera de corrutina")


        } else {
            //TOAST informativo
        }

    }
}