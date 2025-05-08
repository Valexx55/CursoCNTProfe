package edu.cnt.developer.profe

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultadoIMCActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado_imcactivity)
        //obtener la info del intent
        val resultadoImcNumerico = intent.getFloatExtra("IMC_RESULTADO", 0f)
        Log.d("MIAPP", "RES = $resultadoImcNumerico")
        //TODO COMPLETAR LA INTERFAZ DE USUARIO Y EL CÓDIGO DE LA ACTIVIDAD
        //PARA QUE SE MUESTRE UNA IMAGEN QUE REPRESENTE LA CATEGORÍA DE IMC
        //Y EL TÍTULO P EJ: FOTO DE HOMER SIMPSON Y OBESO
        //1 DEFINIR EL LAYOUT (IMAGEVIEW Y UN TEXTVIEW)
        //2 CONSEGUID DE INTERNET FOTOS DE CADA ESTADO Y PONEDLAS EN LA CARPETA DRAWABLE
        //3 PINTAR//RELLENAR EL LAYOUT MEDIANTE EL CÓDIGO
    }
}