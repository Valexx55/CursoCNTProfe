package edu.cnt.developer.profe.fechayhora

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.Calendar

class SeleccionFecha: DialogFragment(), OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //aquí pintamos el calendario
        var micalendario: Dialog
        var calendar: Calendar = Calendar.getInstance()//cogemos el calendario actual
        var anio = calendar.get(Calendar.YEAR)//año actual
        var mes = calendar.get(Calendar.MONTH)//mes
        var dia = calendar.get(Calendar.DATE)//y día actuales

            micalendario = DatePickerDialog(requireActivity(), this, anio, mes, dia)

        return micalendario
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val fechaFinal = "$dayOfMonth/${month+1}/${year}"
        Log.d("MIAPP", "Fecha seleccionada = $fechaFinal")
        (activity as FechaYHoraActivity).actualizarFechaSeleccionada(fechaFinal)
    }
}