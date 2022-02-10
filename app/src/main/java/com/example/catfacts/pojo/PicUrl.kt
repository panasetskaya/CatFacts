package com.example.catfacts.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PicUrl (
    @SerializedName("file")
    @Expose
    val file: String? = null
) {
    override fun toString(): String {
        return file.toString()
    }
}