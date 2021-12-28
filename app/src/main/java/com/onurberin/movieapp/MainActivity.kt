package com.onurberin.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var transaction: FragmentTransaction

    private lateinit var homeFragment: HomeFragment
    private lateinit var popularFragment: PopularFragment
    private lateinit var favoritesFragment: FavoritesFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNav = findViewById(R.id.bottom_navigation_view)

        homeFragment = HomeFragment()
        popularFragment = PopularFragment()
        favoritesFragment = FavoritesFragment()

        setFragment(homeFragment)

        bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.bottom_menu_home -> {
                    setFragment(homeFragment)
                    true
                }
                R.id.bottom_menu_popular-> {
                    setFragment(popularFragment)
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