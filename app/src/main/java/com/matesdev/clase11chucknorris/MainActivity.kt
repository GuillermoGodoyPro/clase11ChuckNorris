package com.matesdev.clase11chucknorris

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private val listadoCategorias = mutableListOf<String>()
    /*
    private lateinit var adapter: BreedsAdapter
    private lateinit var searchView: SearchView
    private lateinit var spinner : Spinner
    private var imagesByBreedList = mutableListOf<String>()
    private var listadoRazas = mutableListOf<String>()
    */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rvCategorias)
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {
        const val BASE_URL ="https://api.chucknorris.io/jokes/"
    }
}