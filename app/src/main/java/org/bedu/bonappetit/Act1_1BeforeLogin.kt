package org.bedu.bonappetit

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

class Act1_1BeforeLogin : AppCompatActivity() {

    companion object {
        const val CHANNEL_COURSES = "CHANNEL_COURSES"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act11_before_login)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setNotificationChannel()
        }

        val buttonLogin = findViewById<Button>(R.id.loginBtn)
        val buttonregister = findViewById<Button>(R.id.registerbtn)
        val buttonInvite = findViewById<Button>(R.id.invitebtn)

        buttonLogin.setOnClickListener {
            startActivity(Intent(applicationContext, Act1_2Login::class.java))
        }

        buttonregister.setOnClickListener {
            startActivity(Intent(applicationContext, Act1_3Register::class.java))
        }

        buttonInvite.setOnClickListener {
            startActivity(Intent(applicationContext, Act2ScannerTableCode::class.java))
            simpleNotification()
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setNotificationChannel(){
        val name = getString(R.string.channel_courses) //Nombre del canal
        val descriptionText = getString(R.string.courses_description) //descriptcion de channel
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(Act1_3Register.CHANNEL_COURSES,name,importance).apply {
            description =descriptionText
        }

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(channel)

    }

    private fun simpleNotification(){
        val notification = NotificationCompat.Builder(this, Act1_3Register.CHANNEL_COURSES)
            .setSmallIcon(R.drawable.iconopizza)
            .setColor(ContextCompat.getColor(this,R.color.colorPrimaryVariant))
            .setContentTitle(getString(R.string.simple_title))
            .setContentText(getString(R.string.simple_body_2))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        NotificationManagerCompat.from(this).run {
            notify(22,notification)
        }
    }
}