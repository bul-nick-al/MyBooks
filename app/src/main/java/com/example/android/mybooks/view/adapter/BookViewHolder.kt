package com.example.android.mybooks.view.adapter

import android.content.Intent
import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.android.mybooks.R
import com.example.android.mybooks.data.SearchBooksResponse
import com.example.android.mybooks.service.model.Book
import kotlinx.android.synthetic.main.book_item.view.*

class BookListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindView(book: SearchBooksResponse.Search.Work.BestBook) {
//        Glide.with(itemView.context).load(itemModel.url).into(itemView.imgView_icon)
        itemView.bookName.text = book.title
        itemView.setOnClickListener {
            val title = itemView.bookName.text
        }
    }

}