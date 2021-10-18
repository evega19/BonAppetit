package org.bedu.bonappetit

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import org.bedu.bonappetit.Adapters.RVAdaperItemsToPay
import org.bedu.bonappetit.BonAppetitOrdersDB.OrdersDB
import org.bedu.bonappetit.Models.MyOrder
import org.bedu.bonappetit.databinding.FragmentAct3Frag3ReadyToCookBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class Act3Frag3ReadyToCook : Fragment() {

    private lateinit var binding : FragmentAct3Frag3ReadyToCookBinding
    private val executor: ExecutorService = Executors.newSingleThreadExecutor()
    private lateinit var listItem: List<MyOrder>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentAct3Frag3ReadyToCookBinding.inflate(inflater,container, false)
        readElements()
        binding.buttonToPay.setOnClickListener {
            findNavController().navigate(R.id.action_act3Frag3ReadyToCook_to_fragMenu, null, Act3Menu.optionAnimateFragment)
        }


        return binding.root
    }

    private fun readElements(){
        executor.execute(Runnable {
            listItem = OrdersDB.getInstance(context = binding.root.context)?.OrdersDao()?.getMyOrders()!!
            Handler(Looper.getMainLooper()).post(Runnable {
                binding.itemList.adapter = RVAdaperItemsToPay(listItem)
                binding.itemList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            })
        })
    }
}