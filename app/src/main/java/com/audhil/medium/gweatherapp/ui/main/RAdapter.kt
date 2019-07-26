package com.audhil.medium.gweatherapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.audhil.medium.gweatherapp.data.model.api.response.ForecastDay
import com.audhil.medium.gweatherapp.databinding.ItemLayoutBinding

class RAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val items: MutableList<ForecastDay> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ItemViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder)
            holder.bindTo(items[position], position)
    }

    //  add items
    fun addItems(forecastList: MutableList<ForecastDay>?) =
        forecastList?.let {
            items.clear()
            items.addAll(it)
            notifyDataSetChanged()
        }

    //  View Holder
    inner class ItemViewHolder(
        private val itemLayoutBinding: ItemLayoutBinding    //  item_layout.xml
    ) : RecyclerView.ViewHolder(itemLayoutBinding.root) {

        fun bindTo(forecastDay: ForecastDay, position: Int) {
            itemLayoutBinding.forecast = forecastDay
            itemLayoutBinding.divider.visibility = if (position == items.size - 1)
                View.GONE
            else
                View.VISIBLE
            itemLayoutBinding.executePendingBindings()
        }
    }
}