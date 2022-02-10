package com.example.catfacts.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class CatFact (
    @PrimaryKey
    @SerializedName("_id")
    @Expose
    val Factid: String,

    @SerializedName("text")
    @Expose
    val text: String? = null
)