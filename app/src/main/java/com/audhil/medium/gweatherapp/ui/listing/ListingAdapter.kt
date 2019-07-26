package com.audhil.medium.gweatherapp.ui.listing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.audhil.medium.gweatherapp.databinding.ListingItemBinding

class ListingAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val items: MutableList<City> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ItemViewHolder(ListingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder)
            holder.bindTo(items[position], position)
    }

    //  add items
    fun addItems(cityList: List<City>) =
        cityList.let {
            items.clear()
            items.addAll(it)
            notifyDataSetChanged()
        }


    //  View Holder
    inner class ItemViewHolder(
        private val itemLayoutBinding: ListingItemBinding    //  listing_item.xml
    ) : RecyclerView.ViewHolder(itemLayoutBinding.root) {

        fun bindTo(city: City, position: Int) {
            itemLayoutBinding.cityResponse = city
            itemLayoutBinding.divider.visibility = if (position == items.size - 1)
                View.GONE
            else
                View.VISIBLE
            itemLayoutBinding.executePendingBindings()
        }
    }

    //  simple POJO

    class City(
        val cityName: String? = null,
        val tempC: String? = null,
        val tempF: String? = null
    )
}