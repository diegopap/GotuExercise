package com.gotu.exercise

import com.gotu.exercise.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class RandomUserApplication : DaggerApplication() {
  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    return DaggerAppComponent.builder().application(this).build()
  }
}