package com.zennymorh.bakingapp.ui.step

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zennymorh.bakingapp.R
import com.zennymorh.bakingapp.model.Step

class StepAdapter(private var stepList: ArrayList<Step>):
RecyclerView.Adapter<StepAdapter.StepViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return StepViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = stepList.size

    override fun onBindViewHolder(holder: StepViewHolder, position: Int) {
        val step: Step = stepList[position]
        holder.bind(step)
    }

    fun updateSteps(list: ArrayList<Step>) {
        stepList = list
        notifyDataSetChanged()
    }


    inner class StepViewHolder(inflater: LayoutInflater, parent: ViewGroup):
        RecyclerView.ViewHolder(inflater
            .inflate(R.layout.step_item, parent, false)) {
        private var stepName: TextView? = null
        private var index: TextView? = null

        init {
            stepName = itemView.findViewById(R.id.stepName)
            index = itemView.findViewById(R.id.index)
        }

        fun bind(step: Step) {
            stepName?.text = step.shortDescription
            val number = 1 + step.id
            index?.text = number.toString()
        }
    }
}