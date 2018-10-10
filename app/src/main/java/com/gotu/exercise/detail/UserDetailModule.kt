package com.gotu.exercise.detail

import com.gotu.exercise.di.ActivityScoped
import dagger.Binds
import dagger.Module

@Module
abstract class UserDetailModule {

  @ActivityScoped
  @Binds
  internal abstract fun presenter(presenter: UserDetailPresenter): UserDetailContract.Presenter

}