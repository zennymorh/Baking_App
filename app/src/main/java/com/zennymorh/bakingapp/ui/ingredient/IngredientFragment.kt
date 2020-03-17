package com.zennymorh.bakingapp.ui.ingredient

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zennymorh.bakingapp.databinding.IngredientFragmentBinding
import com.zennymorh.bakingapp.model.Ingredient


class IngredientFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val ingredients = arguments?.getParcelableArrayList<Ingredient>("Ingredients") as ArrayList<Ingredient>
        val ingredientAdapter = IngredientAdapter(ingredients)

        ingredientAdapter.updateIngredients(ingredients)

        val binding = IngredientFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.ingredientList.adapter = ingredientAdapter

        return binding.root
    }


}
