package com.example.note.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import com.example.note.R
import com.example.note.ui.fragments.EditableFragment
import com.example.note.ui.fragments.ListFragment
import com.example.note.viewmodel.NotesListViewModel
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProvider(this).get(NotesListViewModel::class.java)
        val isLandscape = resources.getBoolean(R.bool.isLandscape)
        val id = R.id.fragment_container
        val manager = supportFragmentManager
        val fragment = manager.findFragmentById(id)

        if (fragment == null) {
            manager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(id, ListFragment())
                .commit()
        }

        viewModel.getIsSelected().observe(this) {
            if (it && !isLandscape) {
                manager.beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_container, EditableFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener {
            //TODO(make handling of clicking on elements of the navigation menu)
            false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_options_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_create -> {
                //TODO(implement listener for action create)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}