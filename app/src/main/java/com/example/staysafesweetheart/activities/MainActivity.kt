package com.example.staysafesweetheart.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.staysafesweetheart.R
import com.example.staysafesweetheart.dagger2.DaggerStaySafeComponent
import com.example.staysafesweetheart.dagger2.SettingsModule
import com.example.staysafesweetheart.dagger2.StaySafeComponent
import com.example.staysafesweetheart.databinding.ActivityMainBinding
import com.example.staysafesweetheart.fragments.alert.AlertFragment
import com.example.staysafesweetheart.fragments.settings.SettingsFragmentNavigation
import com.example.staysafesweetheart.viewmodel.MainActivityViewModel
import com.example.staysafesweetheart.viewmodel.MainActivityViewModelFactory
import com.google.android.material.navigation.NavigationView
import javax.inject.Inject


/**
 * Main entry class point of the application (where it all starts).
 * Implements NavigationView.OnNavigationItemSelectedListener in order to listen for item menu
 * selections.
 */
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    @Inject
    lateinit var mainActivityViewModelFactory: MainActivityViewModelFactory

    private lateinit var daggerComponent: StaySafeComponent
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var binding: ActivityMainBinding
    private lateinit var settingsFragmentNavigation: SettingsFragmentNavigation
    private lateinit var mainActivityViewModel: MainActivityViewModel

    // This method should host all the logic that would reside in a constructor otherwise.
    // Inject dependencies.
    // Restore saved state
    // Call setContentView().
    // ----------------------------
    // I ask myself this question: is this logic related to initialization of this object?
    // If the answer is negative, I find another home for it.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        daggerComponent =
            DaggerStaySafeComponent.builder().settingsModule(SettingsModule(applicationContext!!))
                .build()

        daggerComponent.inject(this)

        mainActivityViewModel =
            ViewModelProvider(
                this,
                mainActivityViewModelFactory
            ).get(MainActivityViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Makes the menu items pictures show their original colors
        binding.navView.itemIconTintList = null

        // Set a Toolbar to act as the ActionBar for this Activity window.
        // Toolbar = A primary toolbar within the activity that may display the activity title,
        // application-level navigation affordances, and other interactive items.
        setSupportActionBar(binding.toolbar)

        // Construct a new ActionBarDrawerToggle with a Toolbar and a Drawer menu layout
        toggle = ActionBarDrawerToggle(
            this, binding.drawerLayout, binding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        // Sets the first fragment to be the Alert fragment
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id,
                AlertFragment()
            )
            .commit()
    }

    // Registration of View click listeners
    // Subscription to observables (general observables, not necessarily Rx)
    // Reflect the current state into UI (UI update)
    // Functional flows
    // Initialization of asynchronous functional flows
    // Resources allocations
    override fun onStart() {
        super.onStart()
        // Sets toggle to listen to drawer events
        binding.drawerLayout.addDrawerListener(toggle)

        // Synchronize the state of the drawer indicator/affordance with the linked DrawerLayout.
        toggle.syncState()

        // To respond to clicks on the menu items
        binding.navView.setNavigationItemSelectedListener(this)

        mainActivityViewModel.idOfCurrentFragment.observe(this, Observer {
            onMenuItemSelectedReact()
        })
    }

    // In this method you will unregister all observers and listeners and release all resources
    // that were allocated in onStart().
    override fun onStop() {
        super.onStop()

        binding.drawerLayout.setOnClickListener(null)
        binding.navView.setNavigationItemSelectedListener(null)
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            if (settingsFragmentNavigation.onBackPressed()) {
                return
            }
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        if (mainActivityViewModel.idOfCurrentFragment.value != menuItem.itemId) {
            mainActivityViewModel.idOfCurrentFragment.value = menuItem.itemId
            menuItem.isChecked = true
        }
        return true
    }

    private fun onMenuItemSelectedReact() {
        when (mainActivityViewModel.idOfCurrentFragment.value) {
            R.id.nav_alert -> supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                AlertFragment()
            ).commit()
            R.id.nav_settings -> {
                settingsFragmentNavigation =
                    SettingsFragmentNavigation()
                supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    settingsFragmentNavigation
                ).commit()
            }
        }
        binding.drawerLayout.closeDrawers()
    }
}
