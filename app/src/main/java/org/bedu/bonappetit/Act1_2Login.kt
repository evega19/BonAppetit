package org.bedu.bonappetit

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Act1_2Login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    companion object {
        const val CHANNEL_COURSES = "CHANNEL_COURSES"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act12_login)
        auth = Firebase.auth


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setNotificationChannel()
        }


        /* Poner esta función cuando haga click el ingresar para que le llegue la notificacion de ofertas*/
        offersNotification()
    }
    /*-----------Código que envia la notificacion con las ofertas------------*/
    @RequiresApi(Build.VERSION_CODES.O)
    private fun setNotificationChannel() {
        val name = getString(R.string.channel_courses) //Nombre del canal
        val descriptionText = getString(R.string.courses_description) //descriptcion de channel
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(Act1_3Register.CHANNEL_COURSES, name, importance).apply {
            description = descriptionText
        }

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(channel)

    }

    private fun offersNotification() {
        val intent = Intent(this, Act1_4Offers::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val notification = NotificationCompat.Builder(this, Act1_3Register.CHANNEL_COURSES)
            .setSmallIcon(R.drawable.iconopizza)
            .setColor(ContextCompat.getColor(this, R.color.colorPrimaryVariant))
            .setContentTitle(getString(R.string.action_title))
            .setContentText(getString(R.string.action_body))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(this).run {
            notify(21, notification)
        }

    }
}
