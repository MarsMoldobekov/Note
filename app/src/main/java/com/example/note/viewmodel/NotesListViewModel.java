package com.example.note.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.note.domain.Callback;
import com.example.note.domain.FirestoreNotesRepository;
import com.example.note.domain.Note;
import com.example.note.domain.NotesRepository;

import java.util.List;

public class NotesListViewModel extends ViewModel {
    private final MutableLiveData<List<Note>> notesLiveData = new MutableLiveData<>();
    private final MutableLiveData<Note> selectedNote = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isSelected = new MutableLiveData<>(false);
    private final NotesRepository repository = new FirestoreNotesRepository();

    public LiveData<List<Note>> getNotesLiveData() {
        return notesLiveData;
    }

    public LiveData<Note> getSelectedNote() {
        return selectedNote;
    }

    public LiveData<Boolean> getIsSelected() {
        return isSelected;
    }

    public void requestNotes() {
        repository.getNotes(new Callback<List<Note>>() {
            @Override
            public void onSuccess(List<Note> value) {
                notesLiveData.setValue(value);
            }

            @Override
            public void onError(Throwable error) {

            }
        });
    }

    public void onNoteClicked(int position) {
        isSelected.setValue(true);
        selectedNote.setValue(notesLiveData.getValue().get(position));
    }

    public void noteClose() {
        isSelected.setValue(false);
    }
}
