package com.audhil.medium.gweatherapp.ui.listing

import android.os.Bundle
import androidx.lifecycle.Observer
import com.audhil.medium.gweatherapp.R
import com.audhil.medium.gweatherapp.databinding.ActivityListingBinding
import com.audhil.medium.gweatherapp.ui.base.BaseLifeCycleActivity
import com.audhil.medium.gweatherapp.util.showVLog

class ListingActivity : BaseLifeCycleActivity<ActivityListingBinding, ListingViewModel>() {

    override val viewModelClass: Class<ListingViewModel>
        get() = ListingViewModel::class.java

    override fun getBindingVariable(): Int = 0

    override fun getLayoutId(): Int = R.layout.activity_listing

    override fun internetAvailable() {
    }

    override fun internetUnAvailable() {
    }

    private val arrayOfCities = arrayOf("Bangalore", "Chennai", "Pune", "Delhi", "Mumbai")

    private val adapter by lazy {
        ListingAdapter()
    }

    private val itemsList by lazy {
        mutableListOf<ListingAdapter.City>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getForeCastLiveData().observe(this, Observer {
            itemsList.add(
                ListingAdapter.City(
                    cityName = it?.location?.name,
                    tempC = it?.current?.tempC,
                    tempF = it?.current?.tempF
                )
            )
            adapter.addItems(itemsList)
        })

//        arrayOfCities.forEach {
//            itemsList.add(
//                ListingAdapter.City(
//                    cityName = it
//                )
//            )
//            adapter.addItems(itemsList)
//        }

        viewDataBinding.recyclerView.adapter = adapter

        viewModel.makeNetworkCall(arrayOfCities)
    }
}