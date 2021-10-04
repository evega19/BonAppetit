package org.bedu.bonappetit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_act_offers.*

class Act1_4Offers : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act_offers)

        btnOffers.setOnClickListener {
            startActivity(Intent(applicationContext, Act2ScannerTableCode::class.java))
        }
    }
}