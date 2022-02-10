package com.example.catfacts.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.catfacts.api.ApiFactory
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val db = CatFactsDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()

    val catFactsList = db?.catFactDao()?.getAllFacts()

    fun loadData() {
        val disposable = ApiFactory.apiServiceFact.getCatFacts()
            .subscribeOn(Schedulers.io())
            .subscribe({
                for (fact in it) {
                    db?.catFactDao()?.insertFact(fact)
                    Log.i("MyRes", "loading succesful")
                }
            }, {
                Log.i("MyRes", "loading UNsuccesful! "+it.message)
            })
        compositeDisposable.add(disposable)
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}