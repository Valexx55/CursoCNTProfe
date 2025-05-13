package edu.cnt.developer.profe

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PerrosActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var razaSeleccionada:String = ""
    lateinit var spinnerRazas: Spinner
    val arrayRazas = arrayOf("affenpinscher", "african", "airedale", "akita", "appenzeller", "australian")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_perros)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        this.spinnerRazas = findViewById(R.id.spinnerRazas)
        val spinnerAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayRazas)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        this.spinnerRazas.adapter = spinnerAdapter
        //programamos la escucha del spinner
        this.spinnerRazas.onItemSelectedListener = this

    }

    override fun onItemSelected(parent: AdapterView<*>?, opcionTocada: View?, position: Int, id: Long) {
        Log.d("MIAPP", "onItemSelected" )
        this.razaSeleccionada = (opcionTocada as TextView).text.toString()
        Log.d("MIAPP", "Raza seleccionada =  ${this.razaSeleccionada}" )
        //parent?.getItemAtPosition(position)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Log.d("MIAPP", "onNothingSelected" )
    }

}