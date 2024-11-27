package ru.zatsoft.retrofit

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(
    fragment: FragmentActivity,
    private val viewPagerList: MutableList<OnViewPagerModel>):
FragmentStateAdapter(fragment){
    override fun getItemCount(): Int {
        return viewPagerList.size
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = PictureFragment()

        fragment.arguments = bundleOf("picture" to viewPagerList[position])
        return fragment
    }
}