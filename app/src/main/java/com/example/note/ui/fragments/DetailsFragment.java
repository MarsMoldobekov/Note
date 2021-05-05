package com.example.note.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.note.R;
import com.example.note.viewmodel.NotesListViewModel;

public class DetailsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NotesListViewModel viewModel = new ViewModelProvider(requireActivity())
                .get(NotesListViewModel.class);
        viewModel.getSelectedNote().observe(getViewLifecycleOwner(), note -> {
            TextView textView = view.findViewById(R.id.description);
            textView.setText((note == null) ? "" : note.getDescription());
        });
    }
}
