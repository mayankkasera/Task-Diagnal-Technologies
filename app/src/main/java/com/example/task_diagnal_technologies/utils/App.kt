package com.example.task_diagnal_technologies.utils

import android.app.Application
import com.example.task_diagnal_technologies.di.component.DaggerNetworkComponent
import com.example.task_diagnal_technologies.di.component.NetworkComponent

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        networkComponent = DaggerNetworkComponent.factory().create(Constants.baseUrl,this)
    }

    companion object {

        private var networkComponent : NetworkComponent? = null

        fun networkComponent(): NetworkComponent? {
            return networkComponent
        }
    }
}