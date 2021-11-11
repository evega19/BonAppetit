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
import org.bedu.bonappetit.inter.ClickListener

class RVAdapItemShowMenu(val menuCategory: ArrayList<Menu>, var listener: ClickListener) : RecyclerView.Adapter<RVAdapItemShowMenu.ViewHolder>(){

    class ViewHolder(view : View,var listener: ClickListener) : RecyclerView.ViewHolder(view), View.OnClickListener{
        val binding = CardItemRvBinding.bind(view)
        // función bind que recibe un objeto Contact y a partir de el genera un contact_item colocando la información en le layout
        fun bind(menu: Menu){
            binding.rvTitle.text = menu.product
            binding.cardRvBook.setOnClickListener(this)
            //binding.rvImage.setImageURI("https://raw.githubusercontent.com/beduExpert/Android-Avanzado-2021/main/Sesion-05/Ejemplo-01/base/app/src/main/res/drawable/bedu.png")
            binding.rvImage.setImageURI(menu.image)
        }

        override fun onClick(view: View?) {
            listener.onClick(view!!,adapterPosition)
        }
    }

    //Cuando no se puede reciclar, lo que hace es llamar al método para crear uno nuevo
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapItemShowMenu.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item_rv, parent, false)
        return ViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: RVAdapItemShowMenu.ViewHolder, position: Int) {
        holder.bind(menuCategory[position])

    }

    override fun getItemCount(): Int {
        return menuCategory.size
    }
}