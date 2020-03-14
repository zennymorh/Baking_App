package com.zennymorh.bakingapp.ui.step

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.zennymorh.bakingapp.R

class StepFragment : Fragment() {

    companion object {
        fun newInstance() = StepFragment()
    }

    private lateinit var viewModel: StepViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.step_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(StepViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
