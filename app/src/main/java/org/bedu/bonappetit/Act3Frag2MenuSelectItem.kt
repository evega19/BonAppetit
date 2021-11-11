package org.bedu.bonappetit

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import org.bedu.bonappetit.BonAppetitOrdersDB.OrdersDB
import org.bedu.bonappetit.Models.MyOrder
import org.bedu.bonappetit.databinding.FragmentAct3Frag2MenuSelectItemBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Frag3MenuSelectItem : Fragment() {

    private lateinit var binding: FragmentAct3Frag2MenuSelectItemBinding


    private val sharedPreferences by lazy{ context?.getSharedPreferences(Act2ScannerTableCode.PREFS_NAME, Context.MODE_PRIVATE) }

    private val executor: ExecutorService = Executors.newSingleThreadExecutor()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentAct3Frag2MenuSelectItemBinding.inflate(inflater,container, false)

        binding.button.setOnClickListener {
            addElement(product,price)
            findNavController().navigate(R.id.action_fragMenuItemSelected_to_fragMenu2, null, Act3Menu.optionAnimateFragment)
        }
        binding.imageView.setImageURI(image)
        return binding.root
    }

    private fun addElement(itemSelected: String, price: Double){
        val newID = sharedPreferences?.getInt(Act3Frag1AllMenu.NElementsSaved,0)!!+1
        val order = MyOrder(newID,itemSelected, price)
        executor.execute(Runnable {
            OrdersDB.getInstance(context = requireContext())?.OrdersDao()?.insertOrder(order)
            Handler(Looper.getMainLooper()).post(Runnable {
                sharedPreferences?.edit()?.putInt(Act3Frag1AllMenu.NElementsSaved,newID)?.apply()
                Toast.makeText(context,"Preparando tu platillo!", Toast.LENGTH_SHORT).show()
            })
        })
    }
}