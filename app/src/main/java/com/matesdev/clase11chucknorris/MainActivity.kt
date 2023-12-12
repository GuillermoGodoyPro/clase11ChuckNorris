package com.matesdev.clase11chucknorris

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private val listadoCategorias = mutableListOf<String>()
    private lateinit var adapter: Adapter

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
        adapter = Adapter(listadoCategorias)

        recyclerView.adapter = adapter

        adapter.onItemClickListener = {category ->
            val intent = Intent(this, JokeActivity::class.java)
            intent.putExtra("categoria", category)
            startActivity(intent)

        }


        getListOfCategories()

    }

    private fun getListOfCategories() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java).getCategorias("categories")
            val response = call.body()

            // que corra en la interface de usuario
            runOnUiThread {
                if(call.isSuccessful){
                    response?.let {
                        listadoCategorias.addAll(it)
                        //listado ha sido cambiado y lo tiene que enviar de nuevo
                        adapter.notifyDataSetChanged()
                    }
                //setSpiner()
                } else {
                    showError()
                }
                //hideKeyboard()
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun showError() {
        Toast.makeText(this, "fallo en la llamada", Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val BASE_URL ="https://api.chucknorris.io/jokes/"
    }
}