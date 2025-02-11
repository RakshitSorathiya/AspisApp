package com.aspis.tech.di.module

import android.content.Context
import com.aspis.tech.App
import com.aspis.tech.api.UrlApi
import com.aspis.tech.di.ApplicationContext
import com.aspis.tech.di.BaseUrl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val app: App) {

    @ApplicationContext
    @Provides
    fun provideContext(): Context {
        return app
    }

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = "https://urlhaus-api.abuse.ch"

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        gsonConverterFactory: GsonConverterFactory
    ): UrlApi {
        return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(gsonConverterFactory).build()
            .create(UrlApi::class.java)
    }
}