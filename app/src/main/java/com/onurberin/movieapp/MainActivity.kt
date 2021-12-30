package com.onurberin.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.onurberin.movieapp.view.FavoritesFragment
import com.onurberin.movieapp.view.HomeFragment
import com.onurberin.movieapp.view.PopularFragment

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var transaction: FragmentTransaction

    private lateinit var homeFragment: HomeFragment
    private lateinit var popularAndUpcomingFragment: PopularAndUpcomingFragment
    private lateinit var favoritesFragment: FavoritesFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNav = findViewById(R.id.bottom_navigation_view)

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
                R.id.bottom_menu_popular-> {
                    setFragment(popularAndUpcomingFragment)
                    true
                }
                R.id.bottom_menu_favorites-> {
                    setFragment(favoritesFragment)
                    true
                }
                else -> false
            }
        }

    }

    private fun setFragment(fragment: Fragment){
        transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, fragment)
        transaction.commit()
    }


}