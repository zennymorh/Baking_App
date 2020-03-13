package com.zennymorh.bakingapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecipeListAdapter(private var recipeList: ArrayList<Recipe>): RecyclerView.Adapter<RecipeViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RecipeViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = recipeList.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe: Recipe = recipeList[position]
        holder.bind(recipe)
    }

    fun updateList(list: ArrayList<Recipe>){
        recipeList = list
        notifyDataSetChanged()
    }

}

class RecipeViewHolder(inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_view_item, parent, false)) {
    private var recipeName: TextView? = null

    init {
        recipeName = itemView.findViewById(R.id.recipeName)
    }

    fun bind(recipe: Recipe) {
        recipeName?.text = recipe.name
    }
}
