package com.alamiya.weatherapptask.presentation.weatherDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alamiya.weatherapptask.R
import com.alamiya.weatherapptask.databinding.ItemDailyBinding
import com.alamiya.weatherapptask.domain.models.WeatherContentModel


class WeatherAdapter : ListAdapter<WeatherContentModel, WeatherAdapter.MyViewHolder>(DailyDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder = MyViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_daily, parent, false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binder(getItem(position))
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binder = ItemDailyBinding.bind(itemView)
        fun binder(data: WeatherContentModel) {
            binder.modelData = data
            binder.executePendingBindings()
        }
    }


    class DailyDiffCallback : DiffUtil.ItemCallback<WeatherContentModel>() {
        override fun areItemsTheSame(oldItem: WeatherContentModel, newItem: WeatherContentModel): Boolean =
            oldItem.dt == newItem.dt

        override fun areContentsTheSame(oldItem: WeatherContentModel, newItem: WeatherContentModel): Boolean =
            oldItem.dt == newItem.dt &&
                    oldItem.desc == newItem.desc &&
                    oldItem.max == newItem.max &&
                    oldItem.min == newItem.min &&
                    oldItem.image == newItem.image


    }
}
