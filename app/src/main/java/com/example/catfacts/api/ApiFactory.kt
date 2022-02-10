package com.example.catfacts.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    private const val BASE_FACT_URL = "https://cat-fact.herokuapp.com/"
    private const val BASE_PIC_URL = "https://aws.random.cat/"

    private val retrofitFact = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl(BASE_FACT_URL)
        .build()

    private val retrofitPic = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl(BASE_PIC_URL)
        .build()

    val apiServiceFact = retrofitFact.create(ApiService::class.java)

    val apiServicePic = retrofitPic.create(ApiService::class.java)

}