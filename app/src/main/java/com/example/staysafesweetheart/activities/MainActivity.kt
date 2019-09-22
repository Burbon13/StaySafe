package com.example.staysafesweetheart.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import kotlinx.android.synthetic.main.activity_main.nav_view
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.activity_main.drawer_layout
import kotlinx.android.synthetic.main.activity_main.fragment_container
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.databinding.DataBindingUtil
import com.example.staysafesweetheart.R
import com.example.staysafesweetheart.databinding.ActivityMainBinding
import com.example.staysafesweetheart.fragments.AlertFragment
import com.example.staysafesweetheart.fragments.SettingsFragment
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var binding: ActivityMainBinding

    // This method should host all the logic that would reside in a constructor otherwise.
    // Inject dependencies.
    // Restore saved state
    // Call setContentView().
    // ----------------------------
    // I ask myself this question: is this logic related to initialization of this object?
    // If the answer is negative, I find another home for it.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Makes the menu items pictures show their original colors
        binding.navView.itemIconTintList = null

        // To respond to clicks on the menu items
        binding.navView.setNavigationItemSelectedListener(this)

        // Set a Toolbar to act as the ActionBar for this Activity window.
        // Toolbar = A primary toolbar within the activity that may display the activity title,
        // application-level navigation affordances, and other interactive items.
        setSupportActionBar(toolbar)

        // Construct a new ActionBarDrawerToggle with a Toolbar and a Drawer menu layout
        toggle = ActionBarDrawerToggle(
            this, binding.drawerLayout, binding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        // Sets toggle to listen to drawer events
        binding.drawerLayout.addDrawerListener(toggle)

        // Synchronize the state of the drawer indicator/affordance with the linked DrawerLayout.
        toggle.syncState()

        // Sets the first fragment to be the Alert fragment
        supportFragmentManager.beginTransaction().replace(binding.fragmentContainer.id, AlertFragment())
            .commit()
    }

    override fun onStart() {
        super.onStart()
    }


    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

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
