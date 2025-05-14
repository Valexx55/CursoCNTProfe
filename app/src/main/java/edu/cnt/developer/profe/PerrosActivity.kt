package edu.cnt.developer.profe

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import edu.cnt.developer.profe.databinding.ActivityPerrosBinding

class PerrosActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var razaSeleccionada:String = ""
    //lateinit var spinnerRazas: Spinner
    val arrayRazas = arrayOf("affenpinscher", "african", "airedale", "akita", "appenzeller", "australian")
    private lateinit var binding: ActivityPerrosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        binding = ActivityPerrosBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //this.spinnerRazas = findViewById(R.id.spinnerRazas)
        val spinnerAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayRazas)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //this.spinnerRazas.adapter = spinnerAdapter
        binding.spinnerRazas.adapter = spinnerAdapter
        //programamos la escucha del spinner
        //this.spinnerRazas.onItemSelectedListener = this
        binding.spinnerRazas.onItemSelectedListener = this

    }

    override fun onItemSelected(parent: AdapterView<*>?, opcionTocada: View?, position: Int, id: Long) {
        Log.d("MIAPP", "onItemSelected" )

            this.razaSeleccionada = (opcionTocada as TextView).text.toString()
            Log.d("MIAPP", "Raza seleccionada =  ${this.razaSeleccionada}" )
            //parent?.getItemAtPosition(position)
            //TODO SnackBar y conexión HTTP
            val snack = Snackbar.make(binding.main, "PERRO SELECCIONADO", BaseTransientBottomBar.LENGTH_LONG)
            snack.setAction("CERRAR"){vista->Log.d("MIAPP", "Snackbar tocado")}
            snack.setTextColor(getColor(R.color.azul))
            snack.show()

        //TODO DADO EL SIGUIENTE API https://my-json-server.typicode.com/miseon920/json-api/products
        //definid una nueva actividad y los ficheros de layout y "fila" layout correspondientes




    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Log.d("MIAPP", "onNothingSelected" )
    }

    fun buscarFotos(view: View) {
        if (RedUtil.hayInternet(this))
        {

            Log.d("MIAPP", "Sí hay internet")
            val intentGaleria = Intent(this, GalleriaPerrosActivity::class.java)
            startActivity(intentGaleria)
        } else {
            Log.d("MIAPP", "NO hay internet")
            val toastSinConexion = Toast.makeText(this, "NO HAY CONEXIÓN", Toast.LENGTH_LONG)
            toastSinConexion.show()
        }

    }

}