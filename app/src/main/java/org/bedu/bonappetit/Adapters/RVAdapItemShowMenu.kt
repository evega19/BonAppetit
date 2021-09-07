package org.bedu.bonappetit.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import org.bedu.bonappetit.Models.Menu
import org.bedu.bonappetit.R
import org.bedu.bonappetit.databinding.CardItemRvBinding

class RVAdapItemShowMenu(val menuCategory: ArrayList<Menu>) : RecyclerView.Adapter<RVAdapItemShowMenu.ViewHolder>(){

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val binding = CardItemRvBinding.bind(view)
        // función bind que recibe un objeto Contact y a partir de el genera un contact_item colocando la información en le layout
        fun bind(menu: Menu){
            binding.rvTitle.text = menu.product
        }
    }

    //Cuando no se puede reciclar, lo que hace es llamar al método para crear uno nuevo
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapItemShowMenu.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item_rv, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RVAdapItemShowMenu.ViewHolder, position: Int) {
        holder.bind(menuCategory[position])

    }

    override fun getItemCount(): Int {
        return menuCategory.size
    }
}