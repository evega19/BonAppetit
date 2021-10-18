package org.bedu.bonappetit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.bedu.bonappetit.databinding.FragmentAct3Frag6PaymentSuceedBinding

class Act3Frag6PaymentSuceed : Fragment() {
    private lateinit var binding: FragmentAct3Frag6PaymentSuceedBinding
    private val sharedPreferences by lazy{ context?.getSharedPreferences(Act2ScannerTableCode.PREFS_NAME, Context.MODE_PRIVATE) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentAct3Frag6PaymentSuceedBinding.inflate(inflater, container, false)

        binding.btnAccept.setOnClickListener {
            goToMain()
        }

        return binding.root
    }

    private fun goToMain(){
        sharedPreferences?.edit()?.clear()?.apply()
        val i = Intent(requireContext(), Act1_1BeforeLogin::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(i)
    }

}