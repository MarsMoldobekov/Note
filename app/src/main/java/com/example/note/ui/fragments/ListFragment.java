package com.example.note.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.note.R;
import com.example.note.ui.Context;
import com.example.note.ui.adapters.MyAdapter;
import com.example.note.ui.listeners.RecyclerItemClickListener;

public class ListFragment extends Fragment {
    private Context context;

    @Override
    public void onAttach(@NonNull android.content.Context context) {
        super.onAttach(context);
        if (context instanceof Context) {
            this.context = (Context) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        context = null;
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClicked(View view, int position) {
                        context.onNotePressed(position);
                    }

                    @Override
                    public void onLongItemClicked(View view, int position) {

                    }
                })
        );
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());

        int scrollPosition = 0;

        if (recyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) recyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        recyclerView.setLayoutManager(manager);
        recyclerView.scrollToPosition(scrollPosition);
        MyAdapter adapter = new MyAdapter(context.getPresenter().getNotes());
        recyclerView.setAdapter(adapter);

        return rootView;
    }
}
