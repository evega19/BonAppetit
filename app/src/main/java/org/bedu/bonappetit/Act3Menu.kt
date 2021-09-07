package org.bedu.bonappetit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.realm.Realm
import org.bedu.bonappetit.Models.Menu

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


class Act3Menu : AppCompatActivity() {

    private var menu: List<Menu>? = null

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