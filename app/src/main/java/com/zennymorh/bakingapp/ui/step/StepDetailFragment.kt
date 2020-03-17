package com.zennymorh.bakingapp.ui.step


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.zennymorh.bakingapp.R
import com.zennymorh.bakingapp.model.Step
import kotlinx.android.synthetic.main.fragment_step_detail.*

/**
 * A simple [Fragment] subclass.
 */
class StepDetailFragment : Fragment() {

    val args: StepDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_step_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        description.text = args.stepSelected.description

    }

}
