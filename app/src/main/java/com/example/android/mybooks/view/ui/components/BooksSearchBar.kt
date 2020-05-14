package com.example.android.mybooks.view.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import com.example.android.mybooks.R

class BooksSearchBar(context: Context, attrs: AttributeSet) :
    ConstraintLayout(context, attrs, android.R.attr.editTextStyle) {

    lateinit var onSearch: (keyword: String) -> Unit
    private val textView: EditText

    override fun clearFocus() {
        super.clearFocus()
        textView.clearFocus()
    }


    init {
        inflate(context, R.layout.books_search_bar, this)

        textView = findViewById(R.id.searchTextView)
        val searchButton: ImageButton = findViewById(R.id.searchButton)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.BooksSearchBar)
        textView.hint = attributes.getString(R.styleable.BooksSearchBar_hint)
        searchButton.visibility = View.GONE
        textView.doOnTextChanged { text, start, count, after ->
            if (textView.text.isNotEmpty()) searchButton.visibility =
                View.VISIBLE else searchButton.visibility =
                View.GONE
        }

        searchButton.setOnClickListener {
            onSearch(textView.text.toString())
        }
        attributes.recycle()

    }
}