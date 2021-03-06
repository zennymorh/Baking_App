package com.zennymorh.bakingwithz.ui.step

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zennymorh.bakingwithz.R
import com.zennymorh.bakingwithz.model.Step

typealias StepItemClickListener = (Step) -> Unit

class StepAdapter(private var stepList: ArrayList<Step>, var listener: StepItemClickListener):
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
            .inflate(R.layout.step_item, parent, false)), View.OnClickListener {

        private var stepName: TextView? = null
        private var index: TextView? = null

        init {
            stepName = itemView.findViewById(R.id.stepName)
            index = itemView.findViewById(R.id.index)
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val step = stepList[adapterPosition]
            listener.invoke(step)
        }

        fun bind(step: Step) {
            stepName?.text = step.shortDescription
            index?.text = step.id.toString()
        }
    }
}