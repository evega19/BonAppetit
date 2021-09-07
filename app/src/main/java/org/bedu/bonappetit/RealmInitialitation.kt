package org.bedu.bonappetit

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration
import org.bedu.bonappetit.Models.Menu
import org.json.JSONArray

class RealmInitialitation: Application() {

    override fun onCreate() {
        super.onCreate()
        //inicializamos Realm
        Realm.init(this)
        //guardar nuestro json en un JSON array
        val array = JSONArray(getJsonFile())
        //configuraciónn de nuestra base de datos
        val config = RealmConfiguration.Builder()
            .initialData { realm->
                //Aquí inicializamos los datos iterando cada objeto JSON
                for(i in 0 until array.length()){
                    val c = realm.createObject(Menu::class.java, i)
                    c.category = array.getJSONObject(i).getString("category")
                    c.type = array.getJSONObject(i).getString("type")
                    c.product = array.getJSONObject(i).getString("product")
                    c.price = array.getJSONObject(i).getString("price")
                }
            }.deleteRealmIfMigrationNeeded().name("realmDB.realm").build() //Colocamos el nombre de la DB

        Realm.setDefaultConfiguration(config)
    }


    fun getJsonFile():String{
        return applicationContext.assets.open("menuApp.json").bufferedReader().use{it.readText() }
    }
}