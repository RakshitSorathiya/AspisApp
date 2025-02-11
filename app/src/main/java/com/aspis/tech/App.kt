package com.aspis.tech

import android.app.Application
import com.aspis.tech.di.component.ApplicationComponent
import com.aspis.tech.di.component.DaggerApplicationComponent
import com.aspis.tech.di.module.ApplicationModule

class App : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        dependencyInjection()
    }

    private fun dependencyInjection() {
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}