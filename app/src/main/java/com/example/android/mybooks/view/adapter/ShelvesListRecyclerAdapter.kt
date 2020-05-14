package com.example.android.mybooks.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.mybooks.R
import com.example.android.mybooks.data.ShelvesListResponse


class ShelvesListRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var shelves = listOf<ShelvesListResponse.Shelves.UserShelf>()
    var clickListener: ShelfClickListener? = null
    var checkedShelfName: String? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ShelvesListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.shelf_item, parent, false)
        )
    }

    override fun getItemCount(): Int = shelves.size

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val movieViewHolder = viewHolder as ShelvesListViewHolder
        movieViewHolder.bindView(shelves[position], checkedShelfName, clickListener)
    }

    fun setShelvesList(shelves: List<ShelvesListResponse.Shelves.UserShelf>) {
        this.shelves = shelves
        notifyDataSetChanged()
    }

    fun setActiveShelf(shelfName: String?) {
        checkedShelfName = shelfName
        notifyDataSetChanged()
    }
}