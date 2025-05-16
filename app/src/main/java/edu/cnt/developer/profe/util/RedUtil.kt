package edu.cnt.developer.profe.util

import android.content.Context
import android.net.ConnectivityManager

object RedUtil {

    fun hayInternet(context: Context): Boolean {
        var hay = false

            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            hay = (cm.activeNetwork != null)//accedo a la red activa

        return hay
    }
}