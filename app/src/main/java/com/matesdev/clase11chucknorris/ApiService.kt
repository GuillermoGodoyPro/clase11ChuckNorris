package com.matesdev.clase11chucknorris

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET
    suspend fun getCategorias(@Url url: String): Response<MutableList<String>>

    @GET
    suspend fun getJokeByCategory(@Url url: String): Response<Joke>

}