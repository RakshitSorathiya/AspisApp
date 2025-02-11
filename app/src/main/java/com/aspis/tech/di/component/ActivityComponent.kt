package com.aspis.tech.di.component

import com.aspis.tech.di.ActivityScope
import com.aspis.tech.di.module.ActivityModule
import com.aspis.tech.ui.MainActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class],modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(activity: MainActivity)
}