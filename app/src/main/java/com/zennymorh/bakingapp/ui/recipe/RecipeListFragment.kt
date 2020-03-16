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

    private val recipeAdapter: RecipeListAdapter by lazy{
        RecipeListAdapter(arrayListOf(), onRecipeItemSelected)
    }

    private val onRecipeItemSelected by lazy {
        object: RecipeItemClickListener {
            override fun invoke(recipe: Recipe) {
                val action = RecipeListFragmentDirections.actionRecipeListFragmentToTabFragment(recipe)
                findNavController().navigate(action)

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

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.recipes.observe(viewLifecycleOwner, Observer { newList ->
            recipeAdapter.updateList(newList)
        })

        binding.recipeList.apply {
            adapter = recipeAdapter
        }


        return binding.root
    }

}
