package edu.cnt.developer.profe

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.absoluteValue

/**
 *
 * HACER UNA CALCULADORA DEL ÍNDICE DE MASA CORPORAL
 *
 * imc = PESO (KG) /  ALTURA * ALTURA
 * IMC < 16 -> desnutridos
 * IMC >=16 y <18 -> delgados
 * IMC >=18 y <25 -> ideal
 * IMC >=25 Y <31 -> sobrepeso
 * IMC >=31 -> obeso
 *
 */


class IMCActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_imcactivity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


    }

    //sobreescribimos este método para dibujar un menú en nuestra actividad

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_imc, menu)//ya pinto el menú
        return super.onCreateOptionsMenu(menu)
    }

    //para escuchar las acciones sobre el menu del app bar, debo sobreescribir el método
    //onOptionItemSelected

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("MIAPP", "Menú app bar tocado ${item.itemId} ${item.title} ${item.order}")
        when (item.itemId)
        {
            R.id.opcionLimpiar -> {
                Log.d("MIAPP", "Ha tocado la opción de limpiar")
            }
            R.id.opcionSalir -> {
                Log.d("MIAPP", "Ha tocado la opción de salir")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun calcularImc(view: View) {
        if (view.id == R.id.botonCalcular)
        {
            Log.d("MIAPP", "LA vista tocada es el botón de calcular ${view.id} ")
            Log.d("MIAPP", " absolute = ${R.id.botonCalcular.absoluteValue}")

        }
        //COGER EL PESO
        var pesof : Float = findViewById<EditText>(R.id.editTextPeso).text.toString().toFloat();
        /*var cajatextopeso =  findViewById<EditText>(R.id.editTextPeso)
        var pesostring :String = cajatextopeso.text.toString()
        var pesofloat = pesostring.toFloat()*/
        //COGER LA ALTURA
        var alturaf : Float = findViewById<EditText>(R.id.editTextAltura).text.toString().toFloat();
        //HACER EL CÁCULO
        var imcnum = pesof / (alturaf*alturaf)
        Log.d("MIAPP", "SU IMC numérico es = $imcnum")
        //TODO acceder dinámicamente al recurso string (para i18n)
        val mensajeResutltado = getString(R.string.mensaje_resultado_imc, imcnum)
        val notiToast : Toast = Toast.makeText(this, mensajeResutltado, Toast.LENGTH_LONG)
        notiToast.show() //muestro el mensaje

        val intentResultado : Intent = Intent(this, ResultadoIMCActivity::class.java)
        intentResultado.putExtra("IMC_RESULTADO", imcnum)
        startActivity(intentResultado)//lanzo la actividad nueva
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.d("MIAPP", "CAMBIO EN LA ORIENTACIÓN")
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {

        }else {

        }
    }


}