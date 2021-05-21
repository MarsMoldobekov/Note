package com.example.note.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.note.R;
import com.example.note.domain.Note;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    private final List<Note> notes;

    public NotesAdapter() {
        notes = new ArrayList<>();
    }

    public void addNotes(List<Note> notes) {
        NotesDiffUtilCallback notesDiffUtilCallback = new NotesDiffUtilCallback(this.notes, notes);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(notesDiffUtilCallback);
        this.notes.clear();
        this.notes.addAll(notes);
        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(notes.get(position));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView noteTitle;
        private final TextView noteDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            noteTitle = itemView.findViewById(R.id.note_title);
            noteDate = itemView.findViewById(R.id.note_date);
        }

        public void bind(@NonNull Note note) {
            noteTitle.setText(note.getTitle());
            noteDate.setText(note.getDate().toString());
        }
    }

    static class NotesDiffUtilCallback extends DiffUtil.Callback {
        private final List<Note> oldList;
        private final List<Note> newList;

        public NotesDiffUtilCallback(List<Note> oldList, List<Note> newList) {
            this.oldList = oldList;
            this.newList = newList;
        }

        @Override
        public int getOldListSize() {
            return oldList.size();
        }

        @Override
        public int getNewListSize() {
            return newList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldList.get(oldItemPosition).getId()
                    .equals(newList.get(newItemPosition).getId());
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
        }
    }
}
