package com.clipcodetest.activity

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.clipcodetest.activity.config.injectFeature
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import java.util.*

class ClipCodeTestApplication: Application() {
    override fun onCreate() {
        super.onCreate()
            instance = this
            initKoin()
            injectFeature()
            Locale.setDefault(Locale.US)
            val config = Configuration()
            config.setLocale(Locale.US)
            baseContext.createConfigurationContext(config)
    }

    private fun initKoin(){
        startKoin {
            if (BuildConfig.DEBUG){
                androidLogger()
            }
            androidContext(this@ClipCodeTestApplication)
        }
    }

    companion object {
        lateinit var instance: ClipCodeTestApplication
            private set
        fun applicationContext() : Context {
            return instance.applicationContext
        }
    }
}
