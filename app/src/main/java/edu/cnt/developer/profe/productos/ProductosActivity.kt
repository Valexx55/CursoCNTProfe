package edu.cnt.developer.profe.productos

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.cnt.developer.profe.util.RedUtil
import edu.cnt.developer.profe.databinding.ActivityProductosBinding
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

class ProductosActivity : AppCompatActivity() {

    lateinit var productoService: ProductoService
    lateinit var listadoProductos: ListadoProductos
    lateinit var binding: ActivityProductosBinding
    lateinit var productosAdapter: ProductosAdapter

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
                programarSlider()
            }
            Log.d("MIAPP", "Fuera de corrutina")


        } else {
            //TOAST informativo
            binding.barraProgreso.visibility = View.INVISIBLE//hacemos que la barra de progreso se haga invisible después de conseguir los productos
            Toast.makeText(this, "SIN ACCESO A INTERNET", Toast.LENGTH_LONG).show()
        }

    }

    private fun programarSlider() {
       binding.sliderproductos.visibility = View.VISIBLE
        //CALCULO ESTADÍSTICOS (MAX, MIN, MEDIA)
       val productoMasCaro =  listadoProductos.maxBy { p -> p.price.toFloat() }
       val productoMasBarato = listadoProductos.minBy { p -> p.price.toFloat() }
       val precioMedio = listadoProductos.map { p -> p.price.toFloat() }.average()
        //LOS DIBUJO EN LA CAJA DE TEXTO
       binding.tvPrecioMasCaro.text = productoMasCaro.price
       binding.tvPrecioMasBarato.text = productoMasBarato.price
       binding.tvPrecioMedio.text = precioMedio.toString()

        binding.sliderproductos.value = productoMasCaro.price.toFloat()
        binding.sliderproductos.valueFrom = productoMasBarato.price.toFloat()
        binding.sliderproductos.valueTo = productoMasCaro.price.toFloat()

        //PROGRAMO LO QUE SE VE AL PULSAR EL SLIDER (ETIQUETA)
       binding.sliderproductos.setLabelFormatter { precio -> precio.roundToInt().toString() + " precio max" }

        //ESCUCHO EL EVENTO DEL ARRASTRE DEL SLIDER
        binding.sliderproductos.addOnChangeListener { slider, valor, fromUser ->
            Log.d("MIAPP", "Valor actual $valor Es del usuario ? $fromUser")
            var listaProductosFiltrada = ListadoProductos()
            listadoProductos.filter { producto -> producto.price.toFloat() <= valor }.toCollection(listaProductosFiltrada)
            this.productosAdapter.listaProductos = listaProductosFiltrada
            //binding.rvlistaproductos.adapter = ProductosAdapter(listaProductosFiltrada)
            this.productosAdapter.notifyDataSetChanged()
        }

    }

    fun mostrarListaProductos ()
    {
        //TODO: ARMAR EL RECYCLER, EL ADAPTER, VIEWHOLDER Y EL LAYAOUT MANAGER, PARA MOSTRAR LA LISTA
        this.productosAdapter = ProductosAdapter(listadoProductos)
        binding.rvlistaproductos.adapter = this.productosAdapter
        binding.rvlistaproductos.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

    }

}