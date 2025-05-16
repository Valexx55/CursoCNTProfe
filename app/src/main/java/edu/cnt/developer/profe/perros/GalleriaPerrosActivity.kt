package edu.cnt.developer.profe.perros

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.cnt.developer.profe.R

class GalleriaPerrosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val raza = intent.getStringExtra("RAZA")
        Log.d("MIAPP", "El usuario quiere buscar fotos de $raza")
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_galleria_perros)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}