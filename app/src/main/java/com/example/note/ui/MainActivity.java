package com.example.note.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.note.R;
import com.example.note.ui.fragments.DetailsFragment;
import com.example.note.ui.fragments.ListFragment;
import com.example.note.viewmodel.NotesListViewModel;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotesListViewModel viewModel = new ViewModelProvider(this)
                .get(NotesListViewModel.class);

        boolean isLandscape = getResources().getBoolean(R.bool.isLandscape);
        int id = R.id.fragment_container;
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(id);

        if (fragment == null) {
            manager.beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(id, new ListFragment())
                    .commit();
        }

        viewModel.getIsSelected().observe(this, aBoolean -> {
            if (aBoolean && !isLandscape) {
                manager.beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_container, new DetailsFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(item -> {
            //TODO --- make handling of clicking on elements of the navigation menu
            return false;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_create) {
            //TODO --- realize listener for action create
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
