package com.cropbit.home_module.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cropbit.databinding.ItemTipsBinding
import com.cropbit.utils.FarmerTips

class FarmerTipsAdapter(private val tipsList: List<FarmerTips>) :
    RecyclerView.Adapter<FarmerTipsAdapter.FarmerTipsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FarmerTipsViewHolder {
        val binding = ItemTipsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FarmerTipsViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: FarmerTipsViewHolder, position: Int) {
        val tip = tipsList[position]
        with(holder.binding){
            title.text = tip.titleTip
            tipDescription.text = tip.tipDescription
        }
    }

    override fun getItemCount(): Int {
        return tipsList.size
    }

    class FarmerTipsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemTipsBinding.bind(itemView)
    }
}
