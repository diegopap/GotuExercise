package com.gotu.exercise.api

import com.google.gson.GsonBuilder
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.Serializable


interface RandomUserService {

    @GET("api/")
    fun getRandomUsers(@Query("page") page: Int, @Query("results") results: Int, @Query("seed") seed: String): Observable<Response>

    companion object {
        var BASE_URL = "https://randomuser.me/"

        val instance by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder()
                        .setLenient()
                        .create()))
                .build()
                .create(RandomUserService::class.java)
        }
    }
}
data class Response(var results: List<Person>)
data class Person(val email: String) : Serializable