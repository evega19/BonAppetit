package org.bedu.bonappetit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import org.bedu.bonappetit.databinding.FragmentAct3Frag5PayByCardBinding


class Act3Frag5PayByCard : Fragment() {

    private lateinit var binding : FragmentAct3Frag5PayByCardBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = FragmentAct3Frag5PayByCardBinding.inflate(inflater,container, false)

        binding.btnUpdate.setOnClickListener {
            findNavController().navigate(R.id.action_act3Frag5PayByCard_to_act3Frag6PaymentSuceed, null, Act3Menu.optionAnimateFragment)
        }

        return binding.root
    }
}