package com.zennymorh.bakingapp.ui.tab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.zennymorh.bakingapp.R
import com.zennymorh.bakingapp.model.Recipe
import com.zennymorh.bakingapp.ui.ingredient.IngredientFragment
import com.zennymorh.bakingapp.ui.step.StepFragment
import kotlinx.android.synthetic.main.fragment_tab.*
import kotlinx.android.synthetic.main.list_view_item.*

class TabFragment : Fragment() {

    private lateinit var tabAdapter: TabAdapter
    val args : TabFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabAdapter = TabAdapter(this, createFragments())
        viewPager.adapter = tabAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when(position) {
                0 -> tab.text = "Steps"
                1 -> tab.text = "Ingredients"
            }
        }.attach()
    }

    private fun createFragments(): ArrayList<Fragment> {
        val fragments: ArrayList<Fragment> = arrayListOf()

        fragments.add(StepFragment().apply {
            arguments = Bundle().apply {
                putParcelableArrayList("Steps", args.recipeSelected.steps)
            }
        })
        fragments.add(IngredientFragment().apply {
            arguments = Bundle().apply {
                putParcelableArrayList("Ingredients", args.recipeSelected.ingredients)
            }
        })

        return fragments
    }
}