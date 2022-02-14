package com.example.catfacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.catfacts.api.ApiFactory
import com.example.catfacts.data.MainViewModel
import com.example.catfacts.pojo.CatFact
import com.example.catfacts.pojo.FavouriteFact
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
    private var isFavourite: Boolean? = false
    private lateinit var viewModel: MainViewModel
    private lateinit var text: String
    private lateinit var id:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        progressBar = findViewById(R.id.progressBar)
        textViewFact = findViewById(R.id.textViewCatFactDetail)
        buttonToFavourite = findViewById(R.id.buttonToFavourites)
        catPic = findViewById(R.id.imageViewCatPic)
        catPic?.setImageResource(R.drawable.koshka)
        disposable = ApiFactory.apiServicePic.getPicUrl()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                picUrl = it.toString()
                Picasso.get().load(picUrl).placeholder(R.drawable.koshka).into(catPic, object: Callback {
                    override fun onSuccess() {
                        Log.i("MyRes", "Test of PICasso loading succesful")
                        progressBar?.visibility = View.GONE
                    }
                    override fun onError(e: Exception?) {
                        Log.i("MyRes", "Picasso loading Unsuccesful!")
                    }
                } )
            }, {
                Log.i("MyRes", "PicUrl loading UNsuccessful! "+it.message)
            })
        val intent = intent;
        if (intent!=null && intent.hasExtra("text")) {
            text = intent.getStringExtra("text").toString()
            id = intent.getStringExtra("id").toString()
            textViewFact?.text = text
            catFact = CatFact(id,text)
        }
        isFavourite = viewModel.isFavById(id)
        if (isFavourite!!) {
            buttonToFavourite?.setText(getString(R.string.delete_from_favourites))
        } else {
            buttonToFavourite?.setText(getString(R.string.add_to_favourites))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
        progressBar?.visibility = View.VISIBLE
    }

    fun onClickChangeFavourite(view: View) {
        if (!isFavourite!!) {
            viewModel.addToFavourite(FavouriteFact(id, text))
            buttonToFavourite?.setText(getString(R.string.delete_from_favourites))
            isFavourite = true
        } else {
            viewModel.deleteFromFavourites(FavouriteFact(id, text))
            buttonToFavourite?.setText(getString(R.string.add_to_favourites))
            isFavourite = false
        }
    }
}