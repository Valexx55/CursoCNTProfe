package edu.cnt.developer.profe.fechayhora

import android.app.Dialog
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.time.LocalDateTime
import java.util.Calendar

class SeleccionHora: DialogFragment(), OnTimeSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var reloj: Dialog
        var calendar: Calendar = Calendar.getInstance()
        var hora = calendar.get(Calendar.HOUR_OF_DAY)
        var min = calendar.get(Calendar.MINUTE)

            reloj = TimePickerDialog(requireActivity(), this, hora, min, true)

        return reloj
    }


    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        val horaFinal:String
        val hora:String
        val minuto:String

        /**
         * //alternativa al formato
         *
         * Sergio Muiños 20/05/2025 19:30 •
         * val horaFormateada = String.format("%02d", hourOfDay)
         * val minutoFormateado = String.format("%02d", minute)
         *
         */
            hora = if (hourOfDay<10) "0$hourOfDay" else hourOfDay.toString()
            minuto = if (minute<10) "0$minute" else minute.toString()
            horaFinal = "$hora:$minuto"

            (activity as FechaYHoraActivity).actualizarHoraSeleccionada(horaFinal)

    }
}