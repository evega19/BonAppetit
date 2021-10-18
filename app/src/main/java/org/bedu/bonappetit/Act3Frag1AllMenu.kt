package org.bedu.bonappetit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.transition.Scene
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import org.bedu.bonappetit.Adapters.RVAdapItemShowMenu
import org.bedu.bonappetit.BonAppetitOrdersDB.OrdersDB
import org.bedu.bonappetit.Models.MyOrder
import org.bedu.bonappetit.databinding.FragmentAct3Frag1AllMenuBinding
import org.bedu.bonappetit.inter.ClickListener
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

var product: String = ""
var price: Double = 0.0

class Act3Frag1AllMenu : Fragment() {

    private lateinit var binding: FragmentAct3Frag1AllMenuBinding

    private lateinit var sceneOne: Scene
    private lateinit var sceneTwo: Scene
    private lateinit var currentScene: Scene

    companion object{
        val NElementsSaved = "Elements"
    }
    private val sharedPreferences by lazy{ context?.getSharedPreferences(Act2ScannerTableCode.PREFS_NAME, Context.MODE_PRIVATE) }

    private val executor: ExecutorService = Executors.newSingleThreadExecutor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding=FragmentAct3Frag1AllMenuBinding.inflate(inflater,container, false)

        /*
        sceneOne = Scene.getSceneForLayout(binding.restaurantPhoto, R.layout.scene_one, context)
        sceneTwo = Scene.getSceneForLayout(binding.restaurantPhoto, R.layout.scene_two, context)
        currentScene = sceneOne


        binding.button.setOnClickListener {
            val transition = TransitionInflater.from(context).inflateTransition(R.transition.bounds)
            currentScene = if(currentScene == sceneOne){
                TransitionManager.go(sceneTwo,transition)
                sceneTwo
            } else{
                TransitionManager.go(sceneOne,transition)
                sceneOne
            }
        }
        */

        binding.IndicatorItemsAddToPay.text = sharedPreferences?.getInt(NElementsSaved,0)!!.toString()

        binding.oneTV   .text = "Entradas"
        binding.twoTV   .text = "Tacos"
        binding.threeTV .text = "Pizzas"
        binding.fourTV  .text = "Ensaladas"
        binding.fiveTV  .text = "Pastas"
        binding.sixTV   .text = "Platos Fuertes"
        binding.sevenTV .text = "Postres"
        binding.eightTV .text = "Sushi"
        binding.nineTV  .text = "Bebida"
        binding.tenTV   .text = "Bedidas Alcohólicas"

        binding.oneRV   .adapter = RVAdapItemShowMenu(menuEntradas     ,object : ClickListener{
            override fun onClick(vista: View, position: Int) {
                menuEntradas[position].apply { addElement(product!!,price!!.toDouble()) }
            }
        })
        binding.twoRV   .adapter = RVAdapItemShowMenu(menuTacos        ,object : ClickListener{
            override fun onClick(vista: View, position: Int) {
                menuTacos[position].apply { addElement(product!!,price!!.toDouble()) }
            }
        })
        binding.threeRV .adapter = RVAdapItemShowMenu(menuPizzas       ,object : ClickListener{
            override fun onClick(vista: View, position: Int) {
                menuPizzas[position].apply { addElement(product!!,price!!.toDouble()) }
            }
        })
        binding.fourRV  .adapter = RVAdapItemShowMenu(menuEnsaladas    ,object : ClickListener{
            override fun onClick(vista: View, position: Int) {
                menuEnsaladas[position].apply { addElement(product!!,price!!.toDouble()) }
            }
        })
        binding.fiveRV  .adapter = RVAdapItemShowMenu(menuPastas       ,object : ClickListener{
            override fun onClick(vista: View, position: Int) {
                menuPastas[position].apply { addElement(product!!,price!!.toDouble()) }
            }
        })
        binding.sixRV   .adapter = RVAdapItemShowMenu(menuPlatosFuertes,object : ClickListener{
            override fun onClick(vista: View, position: Int) {
                menuPlatosFuertes[position].apply { addElement(product!!,price!!.toDouble()) }
            }
        })
        binding.sevenRV .adapter = RVAdapItemShowMenu(menuPostres      ,object : ClickListener{
            override fun onClick(vista: View, position: Int) {
                menuPostres[position].apply { addElement(product!!,price!!.toDouble()) }
            }
        })
        binding.eightRV .adapter = RVAdapItemShowMenu(menuSushi        ,object : ClickListener{
            override fun onClick(vista: View, position: Int) {
                menuSushi[position].apply { addElement(product!!,price!!.toDouble()) }
            }
        })
        binding.nineRV  .adapter = RVAdapItemShowMenu(menuBebida       ,object : ClickListener{
            override fun onClick(vista: View, position: Int) {
                menuBebida[position].apply { addElement(product!!,price!!.toDouble()) }
            }
        })
        binding.tenRV   .adapter = RVAdapItemShowMenu(menuAlcoholica   ,object : ClickListener{
            override fun onClick(vista: View, position: Int) {
                menuAlcoholica[position].apply { addElement(product!!,price!!.toDouble()) }
            }
        })

        binding.oneRV   .layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.twoRV   .layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.threeRV .layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.fourRV  .layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.fiveRV  .layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.sixRV   .layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.sevenRV .layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.eightRV .layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.nineRV  .layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.tenRV   .layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.buttonOrder.setOnClickListener{
            findNavController().navigate(R.id.act3Frag3ReadyToCook, null, Act3Menu.optionAnimateFragment) }
        binding.payment.setOnClickListener {
            findNavController().navigate(R.id.action_fragMenu_to_act3Frag4Payment, null, Act3Menu.optionAnimateFragment) }

        binding.payment.setOnLongClickListener {
            goToPay()
            true
        }
        return binding.root
    }
    private fun addElement(itemSelected: String, priceItem: Double){
        /*val newID = sharedPreferences?.getInt(NElementsSaved,0)!!+1
        val order = MyOrder(newID,itemSelected, price)
        executor.execute(Runnable {
            OrdersDB.getInstance(context = requireContext())?.OrdersDao()?.insertOrder(order)
            Handler(Looper.getMainLooper()).post(Runnable {
                sharedPreferences?.edit()?.putInt(NElementsSaved,newID)?.apply()
                binding.IndicatorItemsAddToPay.text = newID.toString()
                Toast.makeText(context,"Preparando tu platillo!", Toast.LENGTH_SHORT).show()
            })
        })*/
        product = itemSelected
        price = priceItem
        findNavController().navigate(R.id.fragMenuItemSelected, null, Act3Menu.optionAnimateFragment)
    }

    private fun deleteElement(id: Int){
        executor.execute(Runnable {
            OrdersDB.getInstance(context = requireContext())?.OrdersDao()
                    ?.removeOrderById(id)

            Handler(Looper.getMainLooper()).post(Runnable {
                Toast.makeText(context,"Elemento eliminado!", Toast.LENGTH_SHORT).show()
            })
        })
    }


    private fun goToPay(){
        val i = Intent(requireContext(), Act4Payment::class.java)
        //i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(i)
    }
}