package org.bedu.bonappetit.Models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

//Se escribe open para poder heredar
open public class Menu: RealmObject(){
    @PrimaryKey
    var id: Int? = null
    var type: String? = null
    var product: String? = null
    var category: String? = null
    var price: String? = null
    var image: String? = null
}