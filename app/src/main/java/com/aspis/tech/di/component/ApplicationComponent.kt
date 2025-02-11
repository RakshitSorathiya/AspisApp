package com.aspis.tech.di.component

import android.content.Context
import com.aspis.tech.App
import com.aspis.tech.api.UrlApi
import com.aspis.tech.api.UrlRepository
import com.aspis.tech.di.ApplicationContext
import com.aspis.tech.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: App)

    @ApplicationContext
    fun getContext(): Context

    fun getUrlApi(): UrlApi

    fun getTopHeadlineRepository(): UrlRepository
}