package com.onurberin.movieapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.onurberin.movieapp.R


class MainFragment : Fragment() {
    private lateinit var v: View
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var transaction: FragmentTransaction

    private lateinit var homeFragment: HomeFragment
    private lateinit var popularAndUpcomingFragment: PopularAndUpcomingFragment
    private lateinit var favoritesFragment: FavoritesFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        v = view
        bottomNav = v.findViewById(R.id.bottom_navigation_view)

        homeFragment = HomeFragment()
        popularAndUpcomingFragment = PopularAndUpcomingFragment()
        favoritesFragment = FavoritesFragment()

        setFragment(homeFragment)

        bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.bottom_menu_home -> {
                    setFragment(homeFragment)
                    true
                }
                R.id.bottom_menu_popular -> {
                    setFragment(popularAndUpcomingFragment)
                    true
                }
                R.id.bottom_menu_favorites -> {
                    setFragment(favoritesFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun setFragment(fragment: Fragment){
        transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.main_fragment_frameLayout, fragment)
        transaction.commit()
    }

}