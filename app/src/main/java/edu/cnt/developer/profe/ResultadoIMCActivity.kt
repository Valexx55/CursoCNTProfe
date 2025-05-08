package edu.cnt.developer.profe

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultadoIMCActivity : AppCompatActivity() {

    lateinit var textoResultado:TextView //= findViewById(R.id.textoIMC)
    lateinit var imagenResultado:ImageView //= findViewById(R.id.imagenIMC)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado_imcactivity)
        //obtener la info del intent
        val resultadoImcNumerico = intent.getFloatExtra("IMC_RESULTADO", 0f)
        Log.d("MIAPP", "RES = $resultadoImcNumerico")
        //TODO COMPLETAR LA INTERFAZ DE USUARIO Y EL CÓDIGO DE LA ACTIVIDAD
        //PARA QUE SE MUESTRE UNA IMAGEN QUE REPRESENTE LA CATEGORÍA DE IMC
        //Y EL TÍTULO P EJ: FOTO DE HOMER SIMPSON Y OBESO
        //1 DEFINIR EL LAYOUT (IMAGEVIEW Y UN TEXTVIEW) X
        //2 CONSEGUID DE INTERNET FOTOS DE CADA ESTADO Y PONEDLAS EN LA CARPETA DRAWABLE X
        //3 PINTAR//RELLENAR EL LAYOUT MEDIANTE EL CÓDIGO
        this.imagenResultado = findViewById(R.id.imagenIMC)
        this.textoResultado = findViewById(R.id.textoIMC)

        when
        {
            resultadoImcNumerico < 16 -> {
                this.mostrarResultado(R.drawable.imc_desnutrido, TipoIMC.DESNUTRIDO.toString())
            }
            resultadoImcNumerico >= 16 &&  resultadoImcNumerico < 18-> {
                this.mostrarResultado(R.drawable.imc_delgado, TipoIMC.DELGADO.toString())
            }
            resultadoImcNumerico >= 18 &&  resultadoImcNumerico < 25-> {
                this.mostrarResultado(R.drawable.imc_ideal, TipoIMC.IDEAL.toString())
            }
            resultadoImcNumerico >= 25 &&  resultadoImcNumerico < 31-> {
                this.mostrarResultado(R.drawable.imc_sobrepeso, TipoIMC.SOBREPESO.toString())
            }
            resultadoImcNumerico >31-> {
                this.mostrarResultado(R.drawable.imc_obeso, TipoIMC.OBESO.toString())
            }
        }

    }
    fun mostrarResultado (imagen:Int, texto:String): Unit
    {
        this.imagenResultado.setImageResource(imagen)
        this.textoResultado.text = texto
    }
}