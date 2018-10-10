package com.gotu.exercise.di


import com.gotu.exercise.detail.UserDetailActivity
import com.gotu.exercise.detail.UserDetailFragment
import com.gotu.exercise.detail.UserDetailModule
import com.gotu.exercise.list.UserListActivity
import com.gotu.exercise.list.UserListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

  @ActivityScoped
  @ContributesAndroidInjector(modules = [(UserListModule::class)])
  abstract fun userListActivity(): UserListActivity

  @ActivityScoped
  @ContributesAndroidInjector(modules = [(UserDetailModule::class)])
  abstract fun userDetailActivity(): UserDetailActivity

  @ActivityScoped
  @ContributesAndroidInjector(modules = [(UserDetailModule::class)])
  abstract fun userDetailFragment(): UserDetailFragment

}