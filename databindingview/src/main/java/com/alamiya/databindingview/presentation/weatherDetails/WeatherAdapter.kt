package com.alamiya.databindingview.presentation.weatherDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alamiya.databindingview.R
import com.alamiya.databindingview.databinding.ItemDailyBinding
import com.alamiya.weatherapptask.domain.models.WeatherContentModel


class WeatherAdapter(
    private val clickListener: ItemOnCLickListener
) : ListAdapter<WeatherContentModel, WeatherAdapter.MyViewHolder>(DailyDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder = MyViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_daily, parent, false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binder(getItem(position), clickListener)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binder = ItemDailyBinding.bind(itemView)
        fun binder(data: WeatherContentModel, itemOnCLickListener: ItemOnCLickListener) {
            binder.modelData = data
            binder.clickListener = itemOnCLickListener
            binder.executePendingBindings()
        }
    }


    class DailyDiffCallback : DiffUtil.ItemCallback<WeatherContentModel>() {
        override fun areItemsTheSame(
            oldItem: WeatherContentModel,
            newItem: WeatherContentModel
        ): Boolean =
            oldItem.dt == newItem.dt

        override fun areContentsTheSame(
            oldItem: WeatherContentModel,
            newItem: WeatherContentModel
        ): Boolean =
            oldItem.dt == newItem.dt &&
                    oldItem.desc == newItem.desc &&
                    oldItem.max == newItem.max &&
                    oldItem.min == newItem.min &&
                    oldItem.image == newItem.image


    }

    class ItemOnCLickListener(
        val clickListener: (model: WeatherContentModel) -> Unit,
    ) {
        fun onClick(model: WeatherContentModel) = clickListener(model)
    }
}

