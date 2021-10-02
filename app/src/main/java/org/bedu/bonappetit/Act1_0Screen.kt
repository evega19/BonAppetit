package org.bedu.bonappetit

import android.animation.AnimatorInflater
import android.animation.ValueAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

import android.view.WindowManager
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import org.bedu.bonappetit.databinding.ActivityAct10ScreenBinding


private lateinit var btnStart: Button

private lateinit var binding: ActivityAct10ScreenBinding

class Act1_0Screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act10_screen)


//        btnStart = findViewById(R.id.LogoScreen)
//
//
//
//
//
//        btnStart.setOnClickListener{
//            barrelRoll()
//        }

        binding = ActivityAct10ScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        Handler().postDelayed({
            startActivity(Intent(this, Act1_1BeforeLogin::class.java))
            finish()
        }, 2800)





//       barrelRoll()
        screen()



    }











//
//    private fun barrelRoll() {
//        //el valor de nuestro animator va del 0 a 720, dos veces 360º (dos rotaciones de 360º)
//        val valueAnimator = ValueAnimator.ofFloat(0f, 720f)
//
//        //en cada update del animador asignamos la rotación requerida
//        valueAnimator.addUpdateListener {
//            val value = it.animatedValue as Float //obteniendo el valor actual
//            // 2
//            binding.LogoScreen.rotation = value //asignando la posición de rotación
//        }
//
//
//
//        valueAnimator.interpolator = AccelerateDecelerateInterpolator() //el interpolador es lineal
//        valueAnimator.duration = 2800 //la duración es de 1 segundo
//        valueAnimator.start() //correr la animaciónn
//    }



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