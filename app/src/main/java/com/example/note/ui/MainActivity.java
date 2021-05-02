package com.example.note.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.note.domain.Note;
import com.example.note.presenter.Presenter;
import com.example.note.ui.fragments.DetailsFragment;
import com.example.note.ui.fragments.ListFragment;
import com.example.note.R;

public class MainActivity extends AppCompatActivity implements Context {
    private final static String PRESENTER_KEY = "PRESENTER_KEY";
    private Presenter presenter;
    private boolean isLandscape;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            presenter = new Presenter(this);
        } else {
            presenter = savedInstanceState.getParcelable(PRESENTER_KEY);
            presenter.setContext(this);
        }

        isLandscape = getResources().getBoolean(R.bool.isLandscape);
        int id = (isLandscape) ? R.id.fragment_container_land : R.id.fragment_container;

        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(id);

        if (fragment == null) {
            manager.beginTransaction()
                    .replace(id, new ListFragment())
                    .commit();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(PRESENTER_KEY, presenter);
        super.onSaveInstanceState(outState);
    }

    public Presenter getPresenter() {
        return presenter;
    }

    @Override
    public void onNotePressed(int position) {
        Note note = presenter.onNotePressed(position);
        FragmentManager manager = getSupportFragmentManager();

        if (isLandscape) {
            manager.beginTransaction()
                    .replace(R.id.fragment_detail, DetailsFragment.newInstance(note))
                    .commit();
        } else {
            manager.beginTransaction()
                    .replace(R.id.fragment_container, DetailsFragment.newInstance(note))
                    .addToBackStack(null)
                    .commit();
        }
    }
}
