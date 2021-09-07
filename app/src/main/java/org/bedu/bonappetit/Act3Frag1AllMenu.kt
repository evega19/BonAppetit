package org.bedu.bonappetit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.transition.Scene
import android.transition.TransitionInflater
import android.transition.TransitionManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import org.bedu.bonappetit.Adapters.RVAdapItemShowMenu
import org.bedu.bonappetit.databinding.FragmentAct3Frag1AllMenuBinding

class Act3Frag1AllMenu : Fragment() {

    private lateinit var binding: FragmentAct3Frag1AllMenuBinding

    private lateinit var sceneOne: Scene
    private lateinit var sceneTwo: Scene
    private lateinit var currentScene: Scene

    private val sharedPreferences by lazy{ context?.getSharedPreferences(Act2ScannerTableCode.PREFS_NAME, Context.MODE_PRIVATE) }

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

        val manager =  LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.oneRV   .adapter = RVAdapItemShowMenu(menuEntradas     )
        binding.twoRV   .adapter = RVAdapItemShowMenu(menuTacos        )
        binding.threeRV .adapter = RVAdapItemShowMenu(menuPizzas       )
        binding.fourRV  .adapter = RVAdapItemShowMenu(menuEnsaladas    )
        binding.fiveRV  .adapter = RVAdapItemShowMenu(menuPastas       )
        binding.sixRV   .adapter = RVAdapItemShowMenu(menuPlatosFuertes)
        binding.sevenRV .adapter = RVAdapItemShowMenu(menuPostres      )
        binding.eightRV .adapter = RVAdapItemShowMenu(menuSushi        )
        binding.nineRV  .adapter = RVAdapItemShowMenu(menuBebida       )
        binding.tenRV   .adapter = RVAdapItemShowMenu(menuAlcoholica   )

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


        binding.payment.setOnClickListener{
            sharedPreferences?.edit()?.clear()?.apply()
            goToMain()
        }



        return binding.root
    }

    private fun goToMain(){
        val i = Intent(requireContext(), Act2ScannerTableCode::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(i)
    }
}