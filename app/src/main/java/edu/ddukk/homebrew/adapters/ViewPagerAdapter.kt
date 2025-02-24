package edu.ddukk.homebrew.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmetActivity: FragmentActivity) : FragmentStateAdapter(fragmetActivity) {

    private val fragments = mutableListOf<Fragment>()
    private val fragmentTitle = mutableListOf<String>()

    fun addFragment(fragment: Fragment, title: String) {
        fragments.add(fragment)
        fragmentTitle.add(title)
    }

    fun getPageTitle(position: Int): String {
        return fragmentTitle[position]
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}