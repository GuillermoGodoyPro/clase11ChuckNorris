package com.matesdev.clase11chucknorris

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JokeActivity : AppCompatActivity() {

    private lateinit var textViewJoke: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_joke)

        textViewJoke = findViewById(R.id.tvJoke)

        val bundle = intent.extras
        val categoriaSelected = bundle?.getString("categoria", "") ?: ""

        getJokeByCategory(categoriaSelected)
    }


    private fun getJokeByCategory(categoria: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java)
                .getJokeByCategory("random?category=$categoria")
            val response = call.body()

            // que corra en la interface de usuario
            runOnUiThread {
                if(call.isSuccessful){
                    response?.let {
                        textViewJoke.text = it?.joking
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