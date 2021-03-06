package com.example.android.mybooks.view.ui.components

import android.content.Context
import android.text.Html
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.example.android.mybooks.R
import kotlinx.android.synthetic.main.book_list_item.view.*

class BookListItem(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs, 0) {

    fun setTitle(title: String) {
        titleView.text = title
    }

    fun setAuthor(author: String) {
        authorView.text = author
    }

    fun setDescription(description: String) {
        authorView.text = Html.fromHtml(description)
    }

    fun setImage(imageUrl: String) {
        Glide.with(context).load(imageUrl)
            .placeholder(R.drawable.book_cover).fitCenter().into(bookCoverView)
    }

    init {
        inflate(context, R.layout.book_list_item, this)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.BooksSearchBar)

        attributes.recycle()

    }
}