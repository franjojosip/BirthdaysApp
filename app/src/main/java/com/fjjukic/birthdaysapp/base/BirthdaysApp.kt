package com.fjjukic.birthdaysapp.base

import android.app.Application
import com.fjjukic.birthdaysapp.base.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class BirthdaysApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@BirthdaysApp)
            modules(provideModules())
        }
    }

    private fun provideModules(): List<Module> {
        return listOf(
            appModule
        )
    }
}