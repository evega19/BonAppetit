package org.bedu.bonappetit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class Act1_1BeforeLogin : AppCompatActivity() {

    private lateinit var loginbtn: Button
    private lateinit var userbtn: Button
    private lateinit var invitbtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act11_before_login)

        loginbtn = findViewById(R.id.loginBtn)
        userbtn  = findViewById(R.id.userbtn)
        invitbtn = findViewById(R.id.invitbtn)


        loginbtn.setOnClickListener {
            val intent = Intent(this,Act1_2Login::class.java)
            startActivity(intent)

        }

        userbtn.setOnClickListener {
            val intent = Intent(this,Act1_3Register::class.java)
            startActivity(intent)

        }

        invitbtn.setOnClickListener {
            val intent = Intent(this,Act3Frag1AllMenu::class.java)
            startActivity(intent)

        }



    }


}