package com.example.note.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.note.R;
import com.example.note.ui.fragments.DetailsFragment;
import com.example.note.ui.fragments.ListFragment;
import com.example.note.viewmodel.NotesListViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotesListViewModel viewModel = new ViewModelProvider(this)
                .get(NotesListViewModel.class);

        if (savedInstanceState == null) {
            viewModel.requestNotes();
        }

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
    }
}
