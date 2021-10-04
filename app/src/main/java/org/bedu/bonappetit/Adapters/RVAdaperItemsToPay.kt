package org.bedu.bonappetit.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.bedu.bonappetit.Models.MyOrder
import org.bedu.bonappetit.R
import org.bedu.bonappetit.databinding.LayoutRvItemsToPayBinding
import org.bedu.bonappetit.inter.ClickListener

class RVAdaperItemsToPay(var Items: List<MyOrder>) :RecyclerView.Adapter<RVAdaperItemsToPay.ViewHolder>(){

    class ViewHolder(val view: View):RecyclerView.ViewHolder(view){
        private val bindingRV = LayoutRvItemsToPayBinding.bind(view)
        fun modify(myOrder: MyOrder) {
            bindingRV.itemNumber.text = myOrder.id.toString()
            bindingRV.itemName.text = myOrder.itemSelected
            bindingRV.itemCost.text = myOrder.cost.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdaperItemsToPay.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_rv_items_to_pay, parent,false)
        val viewHolder = RVAdaperItemsToPay.ViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: RVAdaperItemsToPay.ViewHolder, position: Int) {
        holder.modify(Items[position])
    }

    override fun getItemCount(): Int {return  Items.size }
}