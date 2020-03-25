package com.zennymorh.bakingapp.ui.step

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.zennymorh.bakingapp.databinding.StepFragmentBinding
import com.zennymorh.bakingapp.model.Recipe
import com.zennymorh.bakingapp.model.Step
import com.zennymorh.bakingapp.ui.main.MainActivity
import com.zennymorh.bakingapp.ui.tab.TabFragmentDirections

class StepFragment : Fragment() {

    lateinit var recipe: Recipe

    private val stepAdapter: StepAdapter by lazy {
        StepAdapter(arrayListOf(), onStepItemSelected)
    }

    private val onStepItemSelected by lazy {
        object: StepItemClickListener {
            override fun invoke(step: Step) {
                val action =  TabFragmentDirections.actionTabFragmentToStepDetailFragment(step)
                findNavController().navigate(action)
                Log.i("tagger", parentFragment.toString())
                Log.i("stepselected", step.description)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = StepFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val steps = arguments?.getParcelableArrayList<Step>("Steps") as ArrayList<Step>

        binding.stepList.adapter = stepAdapter
        binding.stepList.addItemDecoration(DividerItemDecoration(context,
            DividerItemDecoration.VERTICAL))

        stepAdapter.updateSteps(steps)

        (requireActivity() as MainActivity).title = "Step"

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).title = "Step"
    }
}
