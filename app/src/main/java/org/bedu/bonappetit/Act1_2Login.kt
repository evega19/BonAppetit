package org.bedu.bonappetit

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import es.dmoral.toasty.Toasty
import org.bedu.bonappetit.databinding.ActivityAct12LoginBinding
import org.bedu.bonappetit.databinding.ActivityAct13RegisterBinding
import org.bedu.bonappetit.util.Utility
import org.bedu.bonappetit.util.Utility.hideKeyboard
import java.io.IOException

enum class ProviderType{
    BASIC,
    GOOGLE
}
class Act1_2Login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var buttonLogin : Button
    private var email : EditText?=null
    private var password : EditText?=null
    private lateinit var emailText:String
    private lateinit var passwordText:String
    private lateinit var binding: ActivityAct12LoginBinding
    var Ale:Int=1
    private var RC_SIGN_IN : Int  = 100
    private lateinit var googleSignInClient: GoogleSignInClient

    companion object {
        const val CHANNEL_COURSES = "CHANNEL_COURSES"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAct12LoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = Firebase.auth


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setNotificationChannel()
        }
        email = binding.editTextEmail
        password = binding.editTextPassword

        emailText = email!!.text.trim().toString()
        passwordText = password!!.text.trim().toString()

        handleClick()
        /* Poner esta función cuando haga click el ingresar para que le llegue la notificacion de ofertas*/
        offersNotification()

        FirebaseApp.initializeApp(this)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        auth = Firebase.auth

        binding.btnCrearCuenta.setOnClickListener {
            startActivity(Intent(applicationContext, Act1_3Register::class.java))
        }
    }


    private fun handleClick() {

        binding.btnIngresar.setOnClickListener {
            if(!emailText.isEmpty()|| emailText!="" || !passwordText.isEmpty()||passwordText!=""){
                it.hideKeyboard()

                binding.btnIngresar.visibility = View.GONE

                val email = email!!.text.trim().toString()
                val password = password!!.text.trim().toString()

                signIn(email, password)
            }else{
                Toasty.error(this,"Nombre usuario o contraseña vacios", Toasty.LENGTH_SHORT,true).show()
            }

        }
        binding.btnGoogle.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account = task.getResult(ApiException::class.java)!!
            try {
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                if(account!=null){
                    val credential = GoogleAuthProvider.getCredential(account.idToken,null)
                    FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener(this){task->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithCredential:success")
                                val user = auth.currentUser
                                //updateUI(user, null)
                                startActivity(Intent(applicationContext, Act2ScannerTableCode::class.java))
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithCredential:failure", task.exception)
                                updateUI(null, task.exception)
                            }
                        }
                }

            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
                Utility.displaySnackBar(binding.root, "Google sign in failed", this, R.color.red)
            }
        }
    }

    private fun signIn(email: String, password: String) {
        try{
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "signInWithEmail:Succes")
                        val user = auth.currentUser
                        updateUI(user, null)
                    } else {
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        updateUI(null, task.exception)
                    }

                }
        }catch (e: IOException){
            println("vacio desde signin")
        }


    }

    private fun updateUI(user: FirebaseUser?, exception: Exception?) {
        binding.btnGoogle.visibility = View.VISIBLE
        if (exception != null) {
            binding.btnGoogle.visibility = View.VISIBLE
            Utility.displaySnackBar(binding.root, exception.message.toString(), this, R.color.red)
        } else {
            Utility.displaySnackBar(binding.root, "Login was successful: ", this, R.color.green)
            binding.btnGoogle.visibility = View.VISIBLE
        }
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
