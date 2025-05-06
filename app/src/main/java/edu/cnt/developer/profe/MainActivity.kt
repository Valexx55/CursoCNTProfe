package edu.cnt.developer.profe

import android.os.Build
import android.os.Bundle
import android.util.Log
//import android.util.Log
//import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    /**
     * SIEMPRE QUE PROGRAME UNA ACTIVIDAD/PANTALLA
     * TENEMOS DOS PARTES
     *
     * ESTÁTICA: APARIENCIA (XML) - JETPACK COMPOSE
     * DINÁMICA: FUNCIONALIDAD (CLASE KOTLIN) .KT
     */

    /*
    PUNTOS DEL DÍA
    1) TENER INSTALADOR EL IDE (INCORPORA JAVA JDK)
    2) TENER INSTALADO GIT
    3) CREAR UN PROYECTO
    4) CONFIGURAR DISPOSITIVO FÍSICO (OPCIONES DESARROLLADOR)
    5) ESTRUCTURA DE UN PROYECTO

     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.d("MIAPP", "Estoy en oncreate")
        val nombreVersion:String =  obtenerVersionAndroid()
        //nombreVersion = "PEPE"
        Log.d("MIAPP", "Version " + nombreVersion)
        Log.d("MIAPP", "Version = $nombreVersion")
    }

    fun obtenerVersionAndroid (): String
    {
        var nombreVersion:String = ""

        nombreVersion = when (Build.VERSION.SDK_INT) {
                Build.VERSION_CODES.Q -> "ANDROID Q  10"
                Build.VERSION_CODES.R -> "ANDROID R  11"
                Build.VERSION_CODES.VANILLA_ICE_CREAM -> "ANDROID V 15"
                else -> "Versión distinta a 10 u 11"
            }


        return nombreVersion
    }


}