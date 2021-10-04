package org.bedu.bonappetit

import android.animation.AnimatorInflater
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.messaging.FirebaseMessaging
import com.google.android.gms.tasks.OnCompleteListener
import org.bedu.bonappetit.databinding.ActivityMainBinding


private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed({
            startActivity(Intent(this, Act1_1BeforeLogin::class.java))
            finish()
        }, 2800)

        screen()







        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("Error", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            val token = task.result

            Log.d("FCM_TOKEN",token!!)
//            Toast.makeText(baseContext,"FCM token: $token", Toast.LENGTH_SHORT).show()
        })

//        setContentView(R.layout.activity_main)

        /*val analitycs : FirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message", "integración de firebase completa")
        analitycs.logEvent("InitScreen",bundle)*/



    }


    private fun screen() {

        /*ObjectAnimator.ofFloat(arwing, "translationX", 200f).apply {
            duration = 3000
            interpolator = CycleInterpolator(1f)
            start()
        }*/

        AnimatorInflater.loadAnimator(this, R.animator.screen).apply {
            setTarget(binding.LogoScreen)
            start()
        }
    }












}