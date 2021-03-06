package com.zennymorh.bakingwithz.ui.ingredient

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zennymorh.bakingwithz.databinding.IngredientFragmentBinding
import com.zennymorh.bakingwithz.model.Ingredient
import com.zennymorh.bakingwithz.ui.main.MainActivity


class IngredientFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val ingredients = arguments?.
            getParcelableArrayList<Ingredient>("Ingredients") as ArrayList<Ingredient>
        val ingredientAdapter = IngredientAdapter(ingredients)

        ingredientAdapter.updateIngredients(ingredients)

        val binding = IngredientFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.ingredientList.adapter = ingredientAdapter
        // Changing the title of the fragment
        (requireActivity() as MainActivity).title = "Ingredient"

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).title = "Ingredient"
    }
}
