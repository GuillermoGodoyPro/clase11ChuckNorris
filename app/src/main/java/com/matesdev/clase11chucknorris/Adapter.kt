package com.matesdev.clase11chucknorris

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter( private val categorias: List<String>): RecyclerView.Adapter<Adapter.ViewHolder>() {

        lateinit var onItemClickListener: (String) -> Unit
        inner class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
            private val textView: TextView = view.findViewById(R.id.tv_itemlist)

            //Ingressa las categorías dentro del textView
            fun bind(categoria: String) {
                textView.text = categoria

                view.setOnClickListener {
                    onItemClickListener(categoria)
                }

            }


        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return ViewHolder(layoutInflater.inflate(R.layout.item_list, parent, false))
        }

        override fun getItemCount(): Int {
            return categorias.size
        }

        // Setea categorías y manda a bind
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val categoria = categorias[position]
            holder.bind(categoria)
        }



}