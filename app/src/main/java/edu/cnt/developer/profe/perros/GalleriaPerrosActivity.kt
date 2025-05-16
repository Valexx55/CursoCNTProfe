package edu.cnt.developer.profe.perros

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import edu.cnt.developer.profe.R
import edu.cnt.developer.profe.databinding.ActivityGalleriaPerrosBinding
import edu.cnt.developer.profe.databinding.ActivityPerrosBinding
import edu.cnt.developer.profe.productos.ProductoService
import edu.cnt.developer.profe.util.RedUtil
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GalleriaPerrosActivity : AppCompatActivity() {

    lateinit var binding : ActivityGalleriaPerrosBinding
    lateinit var perroService: PerroService
    lateinit var listadoPerros: ListadoPerros
    var raza:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        this.raza = intent.getStringExtra("RAZA") ?: ""
        Log.d("MIAPP", "El usuario quiere buscar fotos de $raza")
        super.onCreate(savedInstanceState)
        binding = ActivityGalleriaPerrosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        obtenerFotos()

    }

    private fun obtenerFotos() {
        //si hay internet
        //TODO COMPLETAR ESTE MÉTODO PARA QUE SE DESCARGUEN LAS FOTOS DE LOS PERROS
        //API https://dog.ceo/api/breed/hound/images
            //comnsumimos el api de perros
        //si no
            //mensaje

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dog.ceo/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        perroService = retrofit.create(PerroService::class.java)

        if (RedUtil.hayInternet(this))
        {
            Log.d("MIAPP", "Hay internet")
            //cargar los productos
            lifecycleScope.launch {
                Log.d("MIAPP", "En corrutina 1")
                listadoPerros = perroService.listarPerrosPorRaza(this@GalleriaPerrosActivity.raza)
                //binding.barraProgreso.visibility = View.INVISIBLE//hacemos que la barra de progreso se haga invisible después de conseguir los productos
                Log.d("MIAPP", "Num FOTOS ${listadoPerros.message.size}")
                mostrarFotos()
            }
            Log.d("MIAPP", "Fuera de corrutina")


        } else {
            //TOAST informativo
            Toast.makeText(this, "SIN ACCESO A INTERNET", Toast.LENGTH_LONG).show()
        }
    }

    private fun mostrarFotos() {
        Log.d("MIAPP", "en mostrarFotos")
    }
}