package com.zennymorh.bakingwithz.ui.recipe

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zennymorh.bakingwithz.R
import com.zennymorh.bakingwithz.model.Recipe

typealias RecipeItemClickListener = (Recipe) -> Unit

class RecipeListAdapter(
    private var recipeList: ArrayList<Recipe>, var listener: RecipeItemClickListener):
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
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_view_item, parent,
            false)),
        View.OnClickListener {

        private var recipeName: TextView? = null
        private var recipeServing: TextView? = null

        init {
            recipeName = itemView.findViewById(R.id.recipeName)
            recipeServing = itemView.findViewById(R.id.recipeServings)
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val recipe = recipeList[adapterPosition]
            listener.invoke(recipe)

        }

        @SuppressLint("SetTextI18n")
        fun bind(recipe: Recipe) {
            recipeName?.text = recipe.name
            recipeServing?.text = "Servings: " + recipe.servings.toString()
        }
    }
}
