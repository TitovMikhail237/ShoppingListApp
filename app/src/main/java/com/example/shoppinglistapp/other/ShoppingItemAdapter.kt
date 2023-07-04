package com.example.shoppinglistapp.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglistapp.R
import com.example.shoppinglistapp.data.db.entities.ShoppingItem
import com.example.shoppinglistapp.databinding.ShoppingItemBinding
import com.example.shoppinglistapp.ui.shoppinglist.ShoppingViewModel

class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

   inner class ShoppingViewHolder(val shoppingItemBinding: ShoppingItemBinding) : RecyclerView.ViewHolder(shoppingItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val shoppingItemBinding = ShoppingItemBinding.inflate(layoutInflater, parent, false)
        return ShoppingViewHolder(shoppingItemBinding)    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
       val curShoppingItem = items[position]

        holder.shoppingItemBinding.tvName.text = curShoppingItem.name
        holder.shoppingItemBinding.tvAmount.text ="${curShoppingItem.amount}"

        holder.shoppingItemBinding.ivDelete.setOnClickListener {
            viewModel.delete(curShoppingItem)
        }

        holder.shoppingItemBinding.ivPlus.setOnClickListener {
            curShoppingItem.amount++
            viewModel.upsert(curShoppingItem)
        }

        holder.shoppingItemBinding.ivMinus.setOnClickListener {
            if(curShoppingItem.amount > 0){
                curShoppingItem.amount--
                viewModel.upsert(curShoppingItem)
            }

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}