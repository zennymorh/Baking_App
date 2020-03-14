package com.zennymorh.bakingapp.ui.recipe

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zennymorh.bakingapp.R
import com.zennymorh.bakingapp.databinding.RecipeListFragmentBinding
import com.zennymorh.bakingapp.model.Recipe


class RecipeListFragment : Fragment() {

    val recipeAdapter: RecipeListAdapter by lazy{
        RecipeListAdapter(arrayListOf(), onRecipeItemSelected)
    }

    val onRecipeItemSelected by lazy {
        object: RecipeItemClickListener {
            override fun invoke(recipe: Recipe) {
                findNavController().navigate(R.id.action_recipeListFragment_to_tabFragment)
            }
        }
    }

    private val viewModel: RecipeListViewModel by lazy {
        ViewModelProviders.of(this).get(RecipeListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = RecipeListFragmentBinding.inflate(inflater)

        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        viewModel.recipes.observe(this, Observer { newList ->
            recipeAdapter.updateList(newList)
        })

        binding.recipeList.apply {
            adapter = recipeAdapter
        }


        return binding.root
    }

}
