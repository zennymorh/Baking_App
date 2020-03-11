package com.zennymorh.bakingapp

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.zennymorh.bakingapp.databinding.RecipeListFragmentBinding


class RecipeListFragment : Fragment() {

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
        return binding.root
    }


}
