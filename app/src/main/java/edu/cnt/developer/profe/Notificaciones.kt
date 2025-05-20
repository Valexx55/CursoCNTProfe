package edu.cnt.developer.profe

import android.annotation.TargetApi
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi

object Notificaciones {

    const val ID_CANAL = "CANAL 1 CNTGPROFE"
    const val NOMBRE_CANAL = "CANAL CNTGPROFE"


    fun lanzarNotificacion (context: Context)
    {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            //creo el canal
            var notificationChannel = crearCanalNotificacion(context)
            //notificationManager.createNotificationChannel(notificationChannel!!)
        }
    }


    //@TargetApi(Build.VERSION_CODES.O)//DESAPARECE EL WARNING Y ADEMÁS, AL LLAMANTE, NO LE OBLIGA A GESTIONAR QUE ESTÁ EN UNA VERSIÓN ADECUADA
    @RequiresApi(Build.VERSION_CODES.O)//DESAPARECE EL WARNING Y ADEMÁS, AL LLAMANTE, LE OBLIGA A GESTIONAR QUE ESTÁ EN UNA VERSIÓN ADECUADA
    fun crearCanalNotificacion (context: Context): NotificationChannel?
    {
        var notificationChannel : NotificationChannel? = null

            notificationChannel = NotificationChannel(ID_CANAL, NOMBRE_CANAL, NotificationManager.IMPORTANCE_DEFAULT)
            notificationChannel.enableLights(true)
            notificationChannel.enableVibration(true)
            notificationChannel.vibrationPattern = longArrayOf(
                500,
                500,
                500,
                500,
                500,
                500,
                500,
                500
            )

            notificationChannel.lightColor = context.getColor(R.color.azul)

        val audioAttributes = AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
            .build()

        notificationChannel.setSound(
            Uri.parse("android.resource://" + context.packageName + "/" + R.raw.snd_noti),
            audioAttributes
        )

        return notificationChannel

    }
}