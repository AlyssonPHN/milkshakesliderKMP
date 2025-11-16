package com.marshall.milkshakekmp

import android.app.Application
import com.marshall.milkshakekmp.di.repositoryModule
import com.marshall.milkshakekmp.di.useCaseModule
import com.marshall.milkshakekmp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MilkshakeKmpApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MilkshakeKmpApp)
            androidLogger()
            modules(repositoryModule, useCaseModule, viewModelModule)
        }
    }
}
