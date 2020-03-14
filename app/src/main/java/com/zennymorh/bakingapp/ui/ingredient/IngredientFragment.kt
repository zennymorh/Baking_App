package com.zennymorh.bakingapp.ui.ingredient

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zennymorh.bakingapp.databinding.IngredientFragmentBinding


class IngredientFragment : Fragment() {

    private val viewModel: IngredientViewModel by lazy {
        ViewModelProviders.of(this).get(IngredientViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = IngredientFragmentBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        return binding.root
    }


}
