package org.bedu.bonappetit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import org.bedu.bonappetit.Adapters.RVAdaperItemsToPay
import org.bedu.bonappetit.BonAppetitOrdersDB.OrdersDB
import org.bedu.bonappetit.Models.MyOrder
import org.bedu.bonappetit.databinding.FragmentAct3Frag3ReadyToCookBinding
import org.bedu.bonappetit.databinding.FragmentAct3Frag4PaymentBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Act3Frag4Payment : Fragment() {

    private lateinit var binding: FragmentAct3Frag4PaymentBinding
    private val executor: ExecutorService = Executors.newSingleThreadExecutor()
    private lateinit var listItem: List<MyOrder>
    private val sharedPreferences by lazy{ context?.getSharedPreferences(Act2ScannerTableCode.PREFS_NAME, Context.MODE_PRIVATE) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentAct3Frag4PaymentBinding.inflate(inflater,container, false)
        readElements()



        binding.buttonToPay.setOnClickListener {
            deleteAll()
            findNavController().navigate(R.id.action_act3Frag4Payment_to_act3Frag5PayByCard, null, Act3Menu.optionAnimateFragment)
        }

        binding.buttonToPayCash.setOnClickListener {
            deleteAll()
            findNavController().navigate(R.id.action_act3Frag4Payment_to_act3Frag6PaymentSuceed, null, Act3Menu.optionAnimateFragment)
        }

        return binding.root
    }

    private fun readElements(){
        executor.execute(Runnable {
            listItem = OrdersDB.getInstance(context = binding.root.context)?.OrdersDao()?.getMyOrders()!!
            Handler(Looper.getMainLooper()).post(Runnable {
                binding.itemList.adapter = RVAdaperItemsToPay(listItem)
                binding.itemList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                var sumTotal: Double = 0.0
                listItem.forEach {
                    sumTotal += it.cost!!
                }
                binding.cost.text = "$ $sumTotal"
            })
        })
    }


    private fun deleteAll(){
        executor.execute(Runnable{
            OrdersDB.getInstance(context= requireContext())?.OrdersDao()?.cleanMyOrder()
            Handler(Looper.getMainLooper()).post(Runnable {
                sharedPreferences?.edit()?.remove(Act3Frag1AllMenu.NElementsSaved)?.apply()
                Toast.makeText(requireContext(),"Gracias por su compra vuelva pronto", Toast.LENGTH_SHORT).show()
            })
        })
    }

    /*private fun goToMain(){
        sharedPreferences?.edit()?.clear()?.apply()
        val i = Intent(this, Act1_1BeforeLogin::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(i)
    }*/
}