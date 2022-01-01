package com.onurberin.movieapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.onurberin.movieapp.R
import com.onurberin.movieapp.adapter.ViewPagerAdapter


class PopularAndUpcomingFragment : Fragment() {

    private lateinit var tabViewPager :ViewPager
    private lateinit var tabLayout :TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular_and_upcoming, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabViewPager = view.findViewById(R.id.tab_viewpager)
        tabLayout = view.findViewById(R.id.tab_tablayout)
       // setSupportActionBar(R.id.tab_toolbar)

        fun setupViewPager(viewpager: ViewPager) {
            val adapter = ViewPagerAdapter(childFragmentManager)//burada supportFragmentManager yazıyodu

            // LoginFragment is the name of Fragment and the Login
            // is a title of tab
            adapter.addFragment(PopularFragment(), "Popular")
            adapter.addFragment(UpcomingFragment(), "Upcoming")
            // setting adapter to view pager.
            viewpager.setAdapter(adapter)
        }

        setupViewPager(tabViewPager)
        tabLayout.setupWithViewPager(tabViewPager)
    }
}