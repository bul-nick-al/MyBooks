package com.example.android.mybooks.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.android.mybooks.R
import com.example.android.mybooks.databinding.MyBooksScreenFragmentFragmentBinding
import com.example.android.mybooks.view.adapter.MyBooksScreenAdapter
import com.example.android.mybooks.viewmodel.MyBooksScreenFragmentViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.action_bar.*


class MyBooksScreenFragment : Fragment() {

    // When requested, this adapter returns a DemoObjectFragment,
    // representing an object in the collection.
    private lateinit var myBooksScreenAdapter: MyBooksScreenAdapter
    private lateinit var viewPager: ViewPager2

    private lateinit var binding: MyBooksScreenFragmentFragmentBinding

    companion object {
        fun newInstance() = MyBooksScreenFragment()
    }

    private lateinit var viewModel: MyBooksScreenFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.my_books_screen_fragment_fragment,
            container,
            false
        )

        viewModel = ViewModelProvider(requireActivity()).get(MyBooksScreenFragmentViewModel::class.java)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        myBooksScreenAdapter = MyBooksScreenAdapter(this)
        viewPager = binding.pager
        viewPager.adapter = myBooksScreenAdapter

        val tabLayout = binding.tabLayout
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text =  when (position) {
                0 -> resources.getString(R.string.finished)
                1 -> resources.getString(R.string.current)
                else -> resources.getString(R.string.planned)
            }
        }.attach()

        // TODO: Use the ViewModel
    }

}
