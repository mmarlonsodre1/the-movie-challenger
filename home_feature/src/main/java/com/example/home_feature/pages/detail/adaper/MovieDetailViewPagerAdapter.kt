package com.example.home_feature.pages.detail.adaper

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class MovieDetailViewPagerAdapter(
    fragment: Fragment
): FragmentStateAdapter(fragment) {
    var items = emptyList<Fragment>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = items.size

    override fun createFragment(position: Int): Fragment {
        return items[position]
    }
}