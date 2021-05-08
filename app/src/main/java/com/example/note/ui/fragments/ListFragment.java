package com.example.note.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.note.R;
import com.example.note.ui.adapters.NotesAdapter;
import com.example.note.ui.listeners.RecyclerItemClickListener;
import com.example.note.viewmodel.NotesListViewModel;

public class ListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        NotesListViewModel viewModel = new ViewModelProvider(requireActivity())
                .get(NotesListViewModel.class);
        NotesAdapter adapter = new NotesAdapter();
        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view);

        if (savedInstanceState == null) {
            viewModel.requestNotes();
        }

        viewModel.getNotesLiveData().observe(getViewLifecycleOwner(), notes -> {
            adapter.addNotes(notes);
            adapter.notifyDataSetChanged();
        });

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                getContext(),
                recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClicked(View view, int position) {
                        viewModel.onNoteClicked(position);
                    }

                    @Override
                    public void onLongItemClicked(View view, int position) {

                    }
                })
        );
        RecyclerView.LayoutManager manager = new LinearLayoutManager(requireContext());

        int scrollPosition = 0;

        if (recyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) recyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        recyclerView.setLayoutManager(manager);
        recyclerView.scrollToPosition(scrollPosition);
        recyclerView.setAdapter(adapter);

        return rootView;
    }
}
