package com.matesdev.clase11chucknorris

import com.google.gson.annotations.SerializedName

data class Joke (
    val categories: List<String>,
    val createdAt: String,
    val iconURL: String,
    val id: String,
    val updatedAt: String,
    val url: String,
   @SerializedName("value") val joking: String
)
