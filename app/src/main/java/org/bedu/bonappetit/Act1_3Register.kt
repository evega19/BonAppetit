package org.bedu.bonappetit


import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color.red
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.bedu.bonappetit.databinding.ActivityAct13RegisterBinding
import org.bedu.bonappetit.util.Utility
import java.lang.Exception


class Act1_3Register : AppCompatActivity() {

    private lateinit var sharedPreferences : SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityAct13RegisterBinding


    companion object {
        const val CHANNEL_COURSES = "CHANNEL_COURSES"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        binding = ActivityAct13RegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        auth = Firebase.auth

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setNotificationChannel()
        }
        val name = binding.editTextName
        val userName = binding.editTextUserName
        val email = binding.editTextEmail
        val password = binding.editTextPassword
        val phone = binding.editTextPhone
        val buttonRegistrar = binding.btnRegister
        val btnTerms = binding.textViewTerms
        var checkTerms = binding.checkRegister

/*
        val name = findViewById<TextView>(R.id.editText_name)
        val userName = findViewById<TextView>(R.id.editText_userName)
        val email = findViewById<TextView>(R.id.editText_email)
        val password = findViewById<TextView>(R.id.editText_password)
        val phone = findViewById<TextView>(R.id.editText_phone)
        val buttonRegistrar = findViewById<Button>(R.id.btnRegister)
        val btnTerms = findViewById<TextView>(R.id.textView_terms)
        var checkTerms = findViewById<CheckBox>(R.id.check_register)*/


        binding.btnRegister.setOnClickListener {
            if((name.text.trim().isNotEmpty()) && (userName.text.trim().isNotEmpty()) && (email.text.trim().toString().isNotEmpty()) && (password.text.trim().toString().isNotEmpty()) && (phone.text.trim().isNotEmpty()) && checkTerms.isChecked){
                startActivity(Intent(applicationContext, Act2ScannerTableCode::class.java))
                simpleNotification()

                createAccount(email.text.trim().toString(),password.text.trim().toString())
                //offersNotification()

            }else{
                Toast.makeText(this,"Error, debes de llenar todos los campos y aceptar los terminos y condiciones.", Toast.LENGTH_LONG).show()
            }
        }

        binding.checkRegister.setOnClickListener {
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
            notify(21, notification)
        }


    }

    private fun createAccount(email:String, password:String){
        println("el correo es:  ${email} la contraseña es: ${password}")
        /*FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener{
            if(it.isSuccessful){
                println("lo hicimos")
            }else{
                showAlert()
            }
        }*/
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){ task ->
                if (task.isSuccessful){
                    Log.d(TAG, "createUserWithEmail:Succes")
                    val user = auth.currentUser
                    updateUI(user,null)
                }else{
                    Log.w(TAG,"createUserWithEmail:failure", task.exception)
                    updateUI(null,task.exception)
                }
            }

    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autentificando al usuario")
        builder.setPositiveButton("Aceptar",null)
        val dialog:AlertDialog = builder.create()
        dialog.show()
    }
    private fun updateUI(user: FirebaseUser?,exception: Exception?){
        if(exception !=null){
            Utility.displaySnackBar(binding.root,exception.message.toString(),this,R.color.red)
        } else{
            Utility.displaySnackBar(binding.root,"Create user was successful", this,R.color.green)
        }
    }




}