package org.bedu.bonappetit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.messaging.FirebaseMessaging
import com.google.android.gms.tasks.OnCompleteListener


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
//            if (!task.isSuccessful) {
//                Log.w("Error", "Fetching FCM registration token failed", task.exception)
//                return@OnCompleteListener
//            }
//
//            val token = task.result
//
//            Log.d("FCM_TOKEN",token!!)
//            Toast.makeText(baseContext,"FCM token: $token", Toast.LENGTH_SHORT).show()
//        })
//
//        setContentView(R.layout.activity_main)

        /*val analitycs : FirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message", "integración de firebase completa")
        analitycs.logEvent("InitScreen",bundle)*/
    }

















}