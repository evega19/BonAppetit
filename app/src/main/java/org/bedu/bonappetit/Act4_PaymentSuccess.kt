package org.bedu.bonappetit


import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.facebook.share.model.ShareHashtag
import com.facebook.share.model.ShareLinkContent
import com.facebook.share.model.SharePhoto
import com.facebook.share.model.SharePhotoContent
import com.facebook.share.widget.ShareDialog
import com.google.firebase.FirebaseApp
import org.bedu.bonappetit.databinding.ActivityAct4PaymentSuccessBinding

class Act4_PaymentSuccess : AppCompatActivity() {
    private lateinit var binding: ActivityAct4PaymentSuccessBinding


    companion object {
        const val CHANNEL_COURSES = "CHANNEL_COURSES"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        binding = ActivityAct4PaymentSuccessBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnCompartir.setOnClickListener {
            val content = ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("https://ibb.co/qyDSCvB"))
                .setQuote("Visita Bon Appetit")
                .setShareHashtag(
                    ShareHashtag.Builder()
                        .setHashtag("#YoEstuveEnBonAppetit")
                        .build()
                )
                .build()

            ShareDialog.show(this, content)

        }

        binding.btnSalir.setOnClickListener {

            startActivity(Intent(applicationContext, Act1_1BeforeLogin::class.java))

        }



    }


}