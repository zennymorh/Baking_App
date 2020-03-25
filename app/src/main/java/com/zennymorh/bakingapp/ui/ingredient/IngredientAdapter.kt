package com.zennymorh.bakingapp.ui.ingredient

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zennymorh.bakingapp.R
import com.zennymorh.bakingapp.model.Ingredient

class IngredientAdapter(private var ingredientList: ArrayList<Ingredient>):
    RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return IngredientViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = ingredientList.size

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        val ingredient: Ingredient = ingredientList[position]
        holder.bind(ingredient)
    }

    fun updateIngredients(list: ArrayList<Ingredient>) {
        ingredientList = list
        notifyDataSetChanged()
    }

    //ViewHolder for the recycler view adapter

    inner class IngredientViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
            RecyclerView.ViewHolder(inflater
                .inflate(R.layout.text_item, parent, false)) {
        private var ingredients: TextView? =  null
        private var quantity: TextView? =  null
        private var measure: TextView? =  null

        init {
            ingredients = itemView.findViewById(R.id.ingredient)
            quantity = itemView.findViewById(R.id.quantity)
            measure = itemView.findViewById(R.id.measure)
        }

        fun bind(ingredient: Ingredient) {
            ingredients?.text = ingredient.ingredient
            quantity?.text = ingredient.quantity.toString()
            measure?.text = ingredient.measure
        }
    }
}
