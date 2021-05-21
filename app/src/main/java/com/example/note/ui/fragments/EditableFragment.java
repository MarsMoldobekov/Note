package com.example.note.ui.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.note.R;
import com.example.note.viewmodel.NotesListViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class EditableFragment extends Fragment {
    NotesListViewModel viewModel;
    EditText editNoteDescription;
    TextInputEditText editNoteTitle;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_editable, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(NotesListViewModel.class);
        editNoteTitle = view.findViewById(R.id.edit_note_title);
        editNoteDescription = view.findViewById(R.id.edit_note_description);

        editNoteTitle.addTextChangedListener(new TextWatcher());
        editNoteDescription.addTextChangedListener(new TextWatcher());

        viewModel.getSelectedNote().observe(getViewLifecycleOwner(), note -> {
            editNoteTitle.setText((note == null) ? "" : note.getTitle());
            editNoteDescription.setText((note == null) ? "" : note.getDescription());
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        viewModel.noteClose();
    }

    static class TextWatcher implements android.text.TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            //TODO --- save text
        }
    }
}
