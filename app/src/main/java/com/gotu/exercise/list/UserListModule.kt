package com.gotu.exercise.list

import com.gotu.exercise.di.ActivityScoped
import dagger.Binds
import dagger.Module

@Module
abstract class UserListModule {

  @ActivityScoped
  @Binds
  internal abstract fun presenter(presenter: UserListPresenter): UserListContract.Presenter

}