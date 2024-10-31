package com.mobile.fetch

import android.app.Application
import com.mobile.fetch.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Fetch: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Fetch)
            modules(appModule)
        }
    }
}