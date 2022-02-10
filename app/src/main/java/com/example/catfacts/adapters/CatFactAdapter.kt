package com.example.catfacts.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.catfacts.R
import com.example.catfacts.pojo.CatFact

class CatFactAdapter: RecyclerView.Adapter<CatFactAdapter.CatFactHolder>(){

    var onfactClick: ((CatFact) -> Unit)? = null

    public var catFactList = arrayListOf<CatFact>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    fun clear() {
        catFactList.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatFactHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.catfact_item, parent, false)
        Log.i("MyRes", "onCreateViewHolder сработал")
        return CatFactHolder(view)
    }

    override fun onBindViewHolder(holder: CatFactHolder, position: Int) {
        val catFact = catFactList[position]
        holder.textViewCatfact.text = catFact.text
        Log.i("MyRes", "onBindViewHolder сработал")
    }

    override fun getItemCount() = catFactList.size

    inner class CatFactHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewCatfact: TextView = itemView.findViewById(R.id.textViewCatFact)
        init {
            itemView.setOnClickListener { onfactClick?.invoke(catFactList[adapterPosition]) }
        }
    }
}