package com.example.calmatemvvm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.calmatemvvm.R
import com.example.calmatemvvm.model.Quote

class FavoritesAdapter(private val favoritesList: List<Quote>) : RecyclerView.Adapter<FavoritesAdapter.QuotesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.favorites_card, parent, false)
        return QuotesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return favoritesList.size
    }

    override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
        val quote = favoritesList[position]
        holder.bind(quote)
    }

    inner class QuotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val favoriteQuote: TextView = itemView.findViewById(R.id.favQuote)
        private val favoriteAuthor: TextView = itemView.findViewById(R.id.favAuthor)

        fun bind(quote: Quote) {
            favoriteQuote.text = quote.quote
            favoriteAuthor.text = quote.author
        }
    }


}