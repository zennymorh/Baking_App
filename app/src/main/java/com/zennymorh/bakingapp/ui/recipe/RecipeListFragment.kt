package com.zennymorh.bakingapp.ui.recipe

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.zennymorh.bakingapp.R
import com.zennymorh.bakingapp.databinding.RecipeListFragmentBinding
import com.zennymorh.bakingapp.model.Recipe
import com.zennymorh.bakingapp.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.recipe_list_fragment.*


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

        viewModel.status.observe(viewLifecycleOwner, Observer {newState ->
            when (newState){
                RecipeApiStatus.LOADING -> {
                    binding.progress.visibility = View.VISIBLE
                }
                RecipeApiStatus.ERROR -> {
                    binding.progress.visibility = View.GONE
                    val snackbar = Snackbar.make(
                        binding.constraintLayout,
                        "Error, please try again",
                        Snackbar.LENGTH_INDEFINITE
                    )
                    snackbar.setAction("Retry") { viewModel.getRecipeList() }
                    snackbar.show()
                }
                RecipeApiStatus.DONE -> {
                    binding.progress.visibility = View.GONE
                }
            }
        })

        binding.recipeList.apply {
            adapter = recipeAdapter
        }
        (requireActivity() as MainActivity).title = "Recipe"
        return binding.root
    }


}
