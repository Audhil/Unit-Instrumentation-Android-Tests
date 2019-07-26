package com.audhil.medium.gweatherapp.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.audhil.medium.gweatherapp.GDelegate
import com.audhil.medium.gweatherapp.repository.AppRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var appRepository: AppRepository

    init {
        GDelegate.INSTANCE.appComponent.inject(this)
    }

    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}