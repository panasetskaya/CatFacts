package com.example.catfacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.example.catfacts.api.ApiFactory
import com.example.catfacts.pojo.CatFact
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Exception

class DetailActivity : AppCompatActivity() {
    private var buttonToFavourite: Button? = null
    private var catPic: ImageView? = null
    private var textViewFact: TextView? = null
    private var catFact: CatFact? = null
    private var disposable: io.reactivex.rxjava3.disposables.Disposable? = null
    private var picUrl: String? = null
    private var progressBar: ProgressBar? = null
    private var isFavourite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        progressBar = findViewById(R.id.progressBar)
        textViewFact = findViewById(R.id.textViewCatFactDetail)
        buttonToFavourite = findViewById(R.id.buttonToFavourites)
        checkIfFavourite()
        catPic = findViewById(R.id.imageViewCatPic)
        disposable = ApiFactory.apiServicePic.getPicUrl()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                picUrl = it.toString()
                Picasso.get().load(picUrl).into(catPic, object: Callback {
                    override fun onSuccess() {
                        Log.i("MyRes", "Test of PICasso loading succesful")
                        progressBar?.visibility = View.INVISIBLE
                    }

                    override fun onError(e: Exception?) {
                        Log.i("MyRes", "Picasso loading UNsuccesful!")
                    }
                } )
                progressBar?.visibility = View.INVISIBLE
            }, {
                Log.i("MyRes", "PicUrl loading UNsuccessful! "+it.message)
            })
        val intent = intent;
        if (intent!=null && intent.hasExtra("text")) {
            val text = intent.getStringExtra("text")
            val id:String = intent.getStringExtra("id").toString()
            textViewFact?.text = text
            catFact = CatFact(id,text)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
        progressBar?.visibility = View.VISIBLE
    }

    fun onClickChangeFavourite(view: View) {
        checkIfFavourite()
    }

    fun checkIfFavourite() {
        if (!isFavourite) {
            buttonToFavourite?.setText(getString(R.string.add_to_favourites))
            isFavourite = true
        } else {
            buttonToFavourite?.setText(getString(R.string.delete_from_favourites))
            isFavourite = false
        }
    }
}