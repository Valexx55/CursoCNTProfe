package edu.cnt.developer.profe.perros

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.cnt.developer.profe.R
import edu.cnt.developer.profe.databinding.ActivityPerrosBinding

class GalleriaPerrosActivity : AppCompatActivity() {

    lateinit var binding : ActivityPerrosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val raza = intent.getStringExtra("RAZA")
        Log.d("MIAPP", "El usuario quiere buscar fotos de $raza")
        super.onCreate(savedInstanceState)
        binding = ActivityPerrosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        obtenerFotos()

    }

    private fun obtenerFotos() {
        //si hay internet
            //comnsumimos el api de perros
        //si no
            //mensaje
    }
}