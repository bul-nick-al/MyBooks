package com.example.android.mybooks.view.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.android.mybooks.view.ui.CurrentBooksFragment
import com.example.android.mybooks.view.ui.FinishedBooksFragment
import com.example.android.mybooks.view.ui.PlannedBooksFragment

class MyBooksScreenAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FinishedBooksFragment()
            1 -> CurrentBooksFragment()
            else -> PlannedBooksFragment()
        }
    }
}
