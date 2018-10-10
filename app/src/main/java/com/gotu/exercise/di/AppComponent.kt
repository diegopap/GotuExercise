package com.gotu.exercise.di

import android.app.Application
import com.gotu.exercise.RandomUserApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
  (ApplicationModule::class),
  (ActivityBindingModule::class),
  (AndroidSupportInjectionModule::class),
  (RetrofitModule::class)
])
interface AppComponent : AndroidInjector<RandomUserApplication> {

  @Component.Builder
  interface Builder {

    @BindsInstance
    fun application(application: Application): AppComponent.Builder

    fun build(): AppComponent
  }
}