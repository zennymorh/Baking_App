package com.zennymorh.bakingapp.ui.recipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.zennymorh.bakingapp.R
import com.zennymorh.bakingapp.model.Recipe

typealias RecipeItemClickListener = (Recipe) -> Unit

class RecipeListAdapter(private var recipeList: ArrayList<Recipe>, var listener: RecipeItemClickListener):
    RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RecipeViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = recipeList.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe: Recipe = recipeList[position]
        holder.bind(recipe)
    }

    fun updateList(list: ArrayList<Recipe>) {
        recipeList = list
        notifyDataSetChanged()
    }

    inner class RecipeViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_view_item, parent, false)),
        View.OnClickListener {

        private var recipeName: TextView? = null
        private var recipeServing: TextView? = null

        init {
            recipeName = itemView.findViewById(R.id.recipeName)
            recipeServing = itemView.findViewById(R.id.recipeServings)
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            var recipe = recipeList[adapterPosition]
            listener.invoke(recipe)

        }

        fun bind(recipe: Recipe) {
            recipeName?.text = recipe.name
            recipeServing?.text = "Servings: " + recipe.servings.toString()
        }
    }
}
