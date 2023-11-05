package com.example.pouyafish.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pouyafish.R
import com.example.pouyafish.TimeModel
import com.example.pouyafish.databinding.RecyclerChooseTimeBinding


class TimeAdapter(private val onItemClicked:(TimeModel) -> Unit):
    androidx.recyclerview.widget.ListAdapter<TimeModel, TimeAdapter.TimeListViewHolder>(DiffCallback){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeListViewHolder {
        return TimeListViewHolder(
            RecyclerChooseTimeBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: TimeListViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(current)
            for (i in currentList) {
                if (i != current) {
                    i.isSelected = false
                } else {

                    Log.i("Log1","current is selected: " + current.isSelected)
                    i.isSelected = !i.isSelected

                }
            }

            Log.i("Log1","isSelected is : " + current.isSelected)
            notifyDataSetChanged()
        }
            //.colorButton(current.isSelected,holder.itemView.findViewById(R.id.main_card),holder.itemView.context)
        holder.bind(current)

    }






    class TimeListViewHolder(private val binding:RecyclerChooseTimeBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(timeModel: TimeModel){
            binding.txtTime.text = timeModel.text
            if(timeModel.isSelected){
                binding.mainCard.setCardBackgroundColor(binding.root.context.getColor(R.color.black))
            }else{
                binding.mainCard.setCardBackgroundColor(binding.root.context.getColor(R.color.purple))
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<TimeModel>() {
            override fun areItemsTheSame(oldItem: TimeModel, newItem: TimeModel): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: TimeModel, newItem: TimeModel): Boolean {
                return oldItem.index == newItem.index
            }
        }
    }

}