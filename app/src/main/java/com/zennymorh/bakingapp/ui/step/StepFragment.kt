package com.zennymorh.bakingapp.ui.step

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zennymorh.bakingapp.databinding.StepFragmentBinding
import com.zennymorh.bakingapp.model.Step

class StepFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = StepFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val steps = arguments?.getParcelableArrayList<Step>("Steps") as ArrayList<Step>
        val stepAdapter = StepAdapter(steps)

        stepAdapter.updateSteps(steps)

        binding.stepList.adapter = stepAdapter

        return binding.root
    }

}
