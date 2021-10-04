package org.bedu.bonappetit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import org.bedu.bonappetit.databinding.ActivityAct12LoginBinding
import org.bedu.bonappetit.ui.login.LoginViewModel

class Act1_2Login : AppCompatActivity() {

    private lateinit var binding: ActivityAct12LoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAct12LoginBinding.inflate(layoutInflater)
        val view= binding.root
        setContentView(view)

    }
}