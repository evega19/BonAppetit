package org.bedu.bonappetit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.navOptions
import io.realm.Realm
import org.bedu.bonappetit.BonAppetitOrdersDB.OrdersDB
import org.bedu.bonappetit.Models.Menu
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

var menuEntradas        : ArrayList<Menu> = arrayListOf()
val menuTacos           : ArrayList<Menu> = arrayListOf()
val menuPizzas          : ArrayList<Menu> = arrayListOf()
val menuEnsaladas       : ArrayList<Menu> = arrayListOf()
val menuPastas          : ArrayList<Menu> = arrayListOf()
val menuPlatosFuertes   : ArrayList<Menu> = arrayListOf()
val menuPostres         : ArrayList<Menu> = arrayListOf()
val menuSushi           : ArrayList<Menu> = arrayListOf()
val menuBebida          : ArrayList<Menu> = arrayListOf()
val menuAlcoholica      : ArrayList<Menu> = arrayListOf()

var menu: List<Menu>? = null

class Act3Menu : AppCompatActivity() {

    companion object{
        val optionAnimateFragment = navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }
        }
    }

    private val executor: ExecutorService = Executors.newSingleThreadExecutor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act3_menu)
        Log.e("Act3Menu", "Init")
        val realm = Realm.getDefaultInstance()
        menu = realm.where(Menu::class.java).findAll()
        Log.e("resultados","Results: ")
        Log.e("resultados","$menu")

        initDB()


    }

    init {
        readElements()
    }

    private fun readElements(){
        executor.execute(Runnable {
            OrdersDB.getInstance(context = this)?.OrdersDao()?.getMyOrders()
            Handler(Looper.getMainLooper()).post(Runnable {
                //Toast.makeText(context,"DBREAD!", Toast.LENGTH_SHORT).show()
            })
        })
    }

    private fun initDB() {
        menu?.forEach {
            when(it.category){
                "Entradas"          -> menuEntradas.add(it)
                "Tacos"             ->menuTacos.add(it)
                "Pizzas"            ->menuPizzas.add(it)
                "Ensaladas"         ->menuEnsaladas.add(it)
                "Pastas"            ->menuPastas.add(it)
                "Platos Fuertes"    ->menuPlatosFuertes.add(it)
                "Postres"           ->menuPostres.add(it)
                "Sushi"             ->menuSushi.add(it)
                "Bebida no Alcohol" ->menuBebida.add(it)
                else                ->menuAlcoholica.add(it)
            }
        }

        Log.e("DB_Init_Entradas",       menuEntradas     .size.toString())
        Log.e("DB_Init_Tacos",          menuTacos        .size.toString())
        Log.e("DB_Init_Pizzas",         menuPizzas       .size.toString())
        Log.e("DB_Init_Ensaladas",      menuEnsaladas    .size.toString())
        Log.e("DB_Init_Pastas",         menuPastas       .size.toString())
        Log.e("DB_Init_Platos Fuertes", menuPlatosFuertes.size.toString())
        Log.e("DB_Init_Postres",        menuPostres      .size.toString())
        Log.e("DB_Init_Sushi",          menuSushi        .size.toString())
        Log.e("DB_Init_Bebida",         menuBebida       .size.toString())
        Log.e("DB_Init_BebidaA",        menuAlcoholica   .size.toString())
    }
}