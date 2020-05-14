package com.example.android.mybooks.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.mybooks.R
import com.example.android.mybooks.data.UserBooksResponse
import kotlinx.android.synthetic.main.book_item_vertical.view.*

class OwnedBookListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindView(book: UserBooksResponse.Review, clickListener: BookClickListener?) {
        Glide.with(itemView.context).load(book.book?.imageUrl)
            .placeholder(R.drawable.book_cover).fitCenter().into(itemView.bookCover)
        itemView.bookTitle.text = book.book?.title

        itemView.setOnClickListener {
            clickListener?.onBookClick(book.book)
        }
    }

}