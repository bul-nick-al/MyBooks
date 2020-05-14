package com.example.android.mybooks.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.mybooks.R
import com.example.android.mybooks.data.ShelvesListResponse
import com.example.android.mybooks.data.UserBooksResponse
import kotlinx.android.synthetic.main.shelf_item.view.*
import java.util.*

class ShelvesListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindView(
        shelf: ShelvesListResponse.Shelves.UserShelf,
        checkedShelfName: String?,
        clickListener: ShelfClickListener?
    ) {
        itemView.singleShelfTitle.text = shelf.name?.replace("-", " ")?.capitalize()

        itemView.singleShelfTitle.isChecked = (checkedShelfName == shelf.name)

        itemView.singleShelfTitle.setOnClickListener { view ->
            shelf.name?.let {
                clickListener?.onShelfClick(it, checkedShelfName == shelf.name)
            }
        }
    }

}