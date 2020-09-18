package com.example.samplehiltapp

import android.app.Application
import android.content.Context
import com.example.samplehiltapp.di.AppContainer
import timber.log.Timber

class SampleApplication : Application(){

    init {
        instance = this
    }

    companion object {
        private var instance: SampleApplication? = null

        fun appContainer(): AppContainer{
            return AppContainer(instance!!.applicationContext)
        }
    }

    override fun onCreate() {
        super.onCreate()
        // initialize for any
        val context: Context = applicationContext
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}