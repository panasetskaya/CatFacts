package com.example.catfacts.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.catfacts.api.ApiFactory
import com.example.catfacts.pojo.FavouriteFact
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val db = CatFactsDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()

    val catFactsList = db?.catFactDao()?.getAllFacts()
    val favFactList = db?.catFactDao()?.getAllFavouriteFacts()

    fun loadData() {
        val disposable = ApiFactory.apiServiceFact.getCatFacts()
            .delaySubscription(3,TimeUnit.SECONDS)
            .repeat()
            .retry()
            .subscribeOn(Schedulers.io())
            .subscribe({
                for (fact in it) {
                    db?.catFactDao()?.insertFact(fact)
                    Log.i("MyRes", "inserting succesful")}
            }, {
                Log.i("MyRes", "loading UNsuccesful! "+it.message)
            })
        compositeDisposable.add(disposable)
    }

    fun addToFavourite(favouriteFact: FavouriteFact) {
        Observable.fromCallable {
            db?.catFactDao()?.insertFavouriteFact(favouriteFact)
        }
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe { Log.i("myRes", "Success: inserting favfact") }
    }

    fun deleteFromFavourites(favouriteFact: FavouriteFact) {
        Observable.fromCallable {
            db?.catFactDao()?.deleteFavouriteFact(favouriteFact)
        }
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe { Log.i("myRes", "Success: deleting favfact") }
    }

    fun isFavById(id: String): Boolean? {
        return db?.catFactDao()?.existsInFavourites(id)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}