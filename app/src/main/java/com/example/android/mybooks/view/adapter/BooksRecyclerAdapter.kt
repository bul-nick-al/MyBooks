package com.example.android.mybooks.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.mybooks.R
import com.example.android.mybooks.service.model.Book

class BooksRecyclerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var listOfBooks = listOf<Book>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BookListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        )
    }

    override fun getItemCount(): Int = listOfBooks.size

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val movieViewHolder = viewHolder as BookListViewHolder
        movieViewHolder.bindView(listOfBooks[position])
    }

    fun setBooksList(listOfBooks: List<Book>) {
        this.listOfBooks = listOfBooks
        notifyDataSetChanged()
    }
}