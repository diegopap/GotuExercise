package com.gotu.exercise.di

import com.google.gson.GsonBuilder
import com.gotu.exercise.api.RandomUserService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class RetrofitModule {

    var BASE_URL = "https://randomuser.me/"

    @Provides
    @Singleton
    fun provideRandomUserApi(): RandomUserService {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder()
                        .setLenient()
                        .create()))
                .build()
                .create(RandomUserService::class.java)
    }

}