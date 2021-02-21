package com.example.findingredients

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class recPageAdapter (
    // dataset that we populate our list with
    private var mDataset: MutableList<Ingredient>
) : RecyclerView.Adapter<recPageAdapter.MyViewHolder>() {


    // Define a custom view holder for each item in the list
    class MyViewHolder internal constructor(textView : View)
        : RecyclerView.ViewHolder(textView) {
        // Add additional variables and values here
        val ingredientName: TextView = itemView.findViewById(R.id.ingredientName)
        val amount: TextView = itemView.findViewById(R.id.ingredientAmount)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : MyViewHolder {
        // Creates the new view for each cell. Instead of a simple TextView,
        // this can be a CardView or a ViewGroup
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ingredient_single_cell, parent, false)
        return MyViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.ingredientName.text = mDataset.get(position).name
        holder.amount.text = mDataset.get(position).amount.toString()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = mDataset.size
}