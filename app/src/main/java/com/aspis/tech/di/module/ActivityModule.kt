package com.aspis.tech.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.aspis.tech.adapter.UrlListAdapter
import com.aspis.tech.api.UrlRepository
import com.aspis.tech.di.ActivityContext
import com.aspis.tech.ui.viewModel.UrlViewModel
import com.aspis.tech.ui.viewModel.ViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity)  {

    @ActivityContext
    @Provides
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideUrlViewModel(urlRepository: UrlRepository): UrlViewModel {
        return  ViewModelProvider(activity,
            ViewModelProviderFactory(UrlViewModel::class) {
                UrlViewModel(urlRepository)
            })[UrlViewModel::class.java]
    }

    @Provides
    fun provideUrlAdapter() = UrlListAdapter(ArrayList())
}