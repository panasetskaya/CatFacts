package com.example.catfacts.api

import com.example.catfacts.pojo.CatFact
import com.example.catfacts.pojo.PicUrl
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET

interface ApiService {

    @GET("facts")
    fun getCatFacts(): Observable<ArrayList<CatFact>>

    @GET("meow")
    fun getPicUrl(): Single<PicUrl>
}