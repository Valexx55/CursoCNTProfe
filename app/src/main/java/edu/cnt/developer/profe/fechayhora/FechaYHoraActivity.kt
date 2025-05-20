package edu.cnt.developer.profe.fechayhora

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethod
import android.view.inputmethod.InputMethodManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.cnt.developer.profe.R
import edu.cnt.developer.profe.databinding.ActivityFechaYhoraBinding

class FechaYHoraActivity : AppCompatActivity(), View.OnFocusChangeListener {

    lateinit var binding: ActivityFechaYhoraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFechaYhoraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cajaFecha.onFocusChangeListener = this
        binding.cajahora.onFocusChangeListener = this

    }

    //? safe call operator
    //!! non-null assertion lanza una excepción si hay un dato nulo
    //?: eLVIS (VALOR por defecto en caso de nulo)
    //val esMayor = if (edad >= 18) "Sí" else "No"
    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        //ocultarTeclado(v)//TODO pediente de revisar el cierre del teclao
       if (hasFocus)
       {

           when (v!!.id)
           {
               R.id.cajaFecha -> {
                   Log.d("MIAAP", "Ha tocado la caja de fecha")
                   val dialogoFragmentCalendario = SeleccionFecha()
                   dialogoFragmentCalendario.show( supportFragmentManager, "CALENDARIO")

               }
               R.id.cajahora -> {
                   Log.d("MIAAP", "Ha tocado la caja de fecha")
                   val dialogoReloj = SeleccionHora()
                   dialogoReloj.show( supportFragmentManager, "RELOJ")
               }

           }

       }


    }

    fun ocultarTeclado (view: View?)
    {
        val servicioinput = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        servicioinput.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    fun actualizarHoraSeleccionada (horaNueva:String) : Unit
    {
        binding.cajahora.setText(horaNueva)
    }

    fun actualizarFechaSeleccionada (fechaNueva:String) : Unit
    {
        binding.cajaFecha.setText(fechaNueva)
    }
}