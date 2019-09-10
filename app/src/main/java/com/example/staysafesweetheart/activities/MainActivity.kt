package com.example.staysafesweetheart.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import kotlinx.android.synthetic.main.activity_main.*
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.staysafesweetheart.R
import com.example.staysafesweetheart.fragments.AlertFragment
import com.example.staysafesweetheart.fragments.SettingsFragment
import com.facebook.stetho.Stetho
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nav_view.itemIconTintList = null

        // To respond to clicks on the menu items
        nav_view.setNavigationItemSelectedListener(this)

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)

        toggle.syncState()

        supportFragmentManager.beginTransaction().replace(fragment_container.id, AlertFragment())
            .commit()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    // Called when menu item is selected
    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.nav_alert -> supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                AlertFragment()
            ).commit()
            R.id.nav_settings -> supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                SettingsFragment()
            ).commit()
        }
        menuItem.isChecked = true
        drawer_layout.closeDrawers()
        return true
    }
}
