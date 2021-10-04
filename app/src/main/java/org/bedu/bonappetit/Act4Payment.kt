package org.bedu.bonappetit

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import org.bedu.bonappetit.Adapters.RVAdaperItemsToPay
import org.bedu.bonappetit.BonAppetitOrdersDB.OrdersDB
import org.bedu.bonappetit.Models.MyOrder
import org.bedu.bonappetit.databinding.ActivityAct4PaymentBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Act4Payment : AppCompatActivity() {
    private val binding by lazy{ ActivityAct4PaymentBinding.inflate(layoutInflater) }
    private val executor: ExecutorService = Executors.newSingleThreadExecutor()
    private lateinit var listItem: List<MyOrder>
    private val sharedPreferences by lazy{ getSharedPreferences(Act2ScannerTableCode.PREFS_NAME, Context.MODE_PRIVATE) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.itemList.adapter = RVAdaperItemsToPay(listItem)
        binding.itemList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        var sumTotal: Double = 0.0
        listItem.forEach {
            sumTotal += it.cost!!
        }
        binding.cost.text = "$ $sumTotal"

        binding.buttonToPay.setOnClickListener {
            deleteAll()
            goToMain()
        }

    }

    init {
        readElements()
    }

    private fun readElements(){
        executor.execute(Runnable {
            listItem = OrdersDB.getInstance(context = this)?.OrdersDao()?.getMyOrders()!!
            Handler(Looper.getMainLooper()).post(Runnable {
                //Toast.makeText(context,"DBREAD!", Toast.LENGTH_SHORT).show()
            })
        })
    }

    private fun deleteAll(){
        executor.execute(Runnable{
            OrdersDB.getInstance(context=this)?.OrdersDao()?.cleanMyOrder()
            Handler(Looper.getMainLooper()).post(Runnable {
                sharedPreferences?.edit()?.remove(Act3Frag1AllMenu.NElementsSaved)?.apply()
                Toast.makeText(this,"Gracias por su compra vuelva pronto", Toast.LENGTH_SHORT).show()
            })
        })
    }

    private fun goToMain(){
        sharedPreferences?.edit()?.clear()?.apply()
        val i = Intent(this, Act2ScannerTableCode::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(i)
    }

}