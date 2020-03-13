package com.zennymorh.bakingapp

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.zennymorh.bakingapp.databinding.RecipeListFragmentBinding


class RecipeListFragment : Fragment() {

    var recipeAdapter = RecipeListAdapter(arrayListOf())

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
            layoutManager = LinearLayoutManager(activity)
            adapter = recipeAdapter
        }
        return binding.root
    }
}
