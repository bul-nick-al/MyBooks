package com.example.android.mybooks.view.adapter

import android.text.Html
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.mybooks.R
import com.example.android.mybooks.data.UserBooksResponse
import kotlinx.android.synthetic.main.book_item.view.*

class MyBooksListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindView(book: UserBooksResponse.Review, clickListener: BookClickListener?) {
        book.book?.title?.let { itemView.item.setTitle(it) }
        book.book?.description?.let { itemView.item.setDescription(it) }
        book.book?.imageUrl?.let { itemView.item.setImage(it) }

        itemView.setOnClickListener {
            clickListener?.onBookClick(book.book)
        }
    }

}