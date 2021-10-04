package org.bedu.bonappetit


import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat


class Act1_3Register : AppCompatActivity() {

    private lateinit var sharedPreferences : SharedPreferences
    private lateinit var editor: SharedPreferences.Editor


    companion object {
        const val CHANNEL_COURSES = "CHANNEL_COURSES"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act13_register)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setNotificationChannel()
        }


        val name = findViewById<TextView>(R.id.editText_name)
        val userName = findViewById<TextView>(R.id.editText_userName)
        val email = findViewById<TextView>(R.id.editText_email)
        val password = findViewById<TextView>(R.id.editText_password)
        val phone = findViewById<TextView>(R.id.editText_phone)
        val buttonRegistrar = findViewById<Button>(R.id.btnRegister)
        val btnTerms = findViewById<TextView>(R.id.textView_terms)
        var checkTerms = findViewById<CheckBox>(R.id.check_register)


        buttonRegistrar.setOnClickListener {
            if((name.text.trim().isNotEmpty()) && (userName.text.trim().isNotEmpty()) && (email.text.trim().isNotEmpty()) && (password.text.trim().isNotEmpty()) && (phone.text.trim().isNotEmpty()) && checkTerms.isChecked){

                startActivity(Intent(applicationContext, Act2ScannerTableCode::class.java))
                simpleNotification()
                //offersNotification()

            }else{
                Toast.makeText(this,"Error, debes de llenar todos los campos y aceptar los terminos y condiciones.", Toast.LENGTH_LONG).show()
            }
        }

        btnTerms.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Terminos y Condiciones")
            builder.setMessage("La actividad del usuario en la aplicación como publicaciones o comentarios estarán sujetos a los presentes términos y condiciones. El usuario se compromete a utilizar el contenido, productos y/o servicios de forma lícita, sin faltar a la moral o al orden público, absteniéndose de realizar cualquier acto que afecte los derechos de terceros o el funcionamiento del sitio web.\n" +
                    "\n" +
                    "El usuario se compromete a proporcionar información verídica en los formularios del sitio web.\n" +
                    "\n" +
                    "El acceso al sitio web no supone una relación entre el usuario y el titular del sitio web.\n" +
                    "\n" +
                    "El usuario manifiesta ser mayor de edad y contar con la capacidad jurídica de acatar los presentes términos y condiciones." +
                    "Conforme a lo establecido en la Ley Federal de Protección de Datos Personales en Posesión de Particulares, el titular de compromete a tomar las medidas necesarias que garanticen la seguridad del usuario, evitando que se haga uso indebido de los datos personales que el usuario proporcione en el sitio web.\n" +
                    "\n" +
                    "El titular corroborará que los datos personales contenidos en las bases de datos sean correctos, verídicos y actuales, así como que se utilicen únicamente con el fin con el que fueron recabados.")
            builder.setPositiveButton("Aceptar", null)

            val dialog = builder.create()
            dialog.show()
        }






    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setNotificationChannel(){
        val name = getString(R.string.channel_courses) //Nombre del canal
        val descriptionText = getString(R.string.courses_description) //descriptcion de channel
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_COURSES,name,importance).apply {
            description =descriptionText
        }

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(channel)

    }

    private fun simpleNotification(){
        val notification = NotificationCompat.Builder(this, CHANNEL_COURSES )
            .setSmallIcon(R.drawable.iconopizza)
            .setColor(ContextCompat.getColor(this,R.color.colorPrimaryVariant))
            .setContentTitle(getString(R.string.simple_title))
            .setContentText(getString(R.string.simple_body))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        NotificationManagerCompat.from(this).run {
            notify(20,notification)
        }
    }

    private fun offersNotification(){
        val intent = Intent(this, Act1_4Offers::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val notification = NotificationCompat.Builder(this, CHANNEL_COURSES )
            .setSmallIcon(R.drawable.iconopizza)
            .setColor(ContextCompat.getColor(this,R.color.colorPrimaryVariant))
            .setContentTitle(getString(R.string.action_title))
            .setContentText(getString(R.string.action_body))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(this).run {
            notify(20, notification)
        }


    }


}