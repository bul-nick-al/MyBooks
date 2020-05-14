package com.example.android.mybooks.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.mybooks.R
import com.example.android.mybooks.data.SearchBooksResponse
import com.example.android.mybooks.service.model.Book

class AllBooksRecyclerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var listOfBooks = listOf<SearchBooksResponse.Search.Work.BestBook>()
    var clickListener: BookClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BookListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        )
    }

    override fun getItemCount(): Int = listOfBooks.size

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val movieViewHolder = viewHolder as BookListViewHolder
        movieViewHolder.bindView(listOfBooks[position], clickListener)
    }

    fun setBooksList(listOfBooks: List<SearchBooksResponse.Search.Work.BestBook>) {
        this.listOfBooks = listOfBooks
        notifyDataSetChanged()
    }
}