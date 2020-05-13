package com.example.android.mybooks.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.mybooks.R
import com.example.android.mybooks.data.UserBooksResponse


class OwnedBooksRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var listOfBooks = listOf<UserBooksResponse.Review>()
    var clickListener: BookClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return OwnedBookListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.book_item_vertical, parent, false)
        )
    }

    override fun getItemCount(): Int = listOfBooks.size

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val movieViewHolder = viewHolder as OwnedBookListViewHolder
        movieViewHolder.bindView(listOfBooks[position], clickListener)
    }

    fun setBooksList(listOfBooks: List<UserBooksResponse.Review>) {
        this.listOfBooks = listOfBooks
        notifyDataSetChanged()
    }
}