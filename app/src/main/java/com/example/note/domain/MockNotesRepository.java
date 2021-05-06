package com.example.note.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MockNotesRepository implements NotesRepository {
    private final List<Note> notes;

    public MockNotesRepository() {
        notes = new ArrayList<>();
        addNotes();
    }

    @Override
    public List<Note> getNotes() {
        return Collections.unmodifiableList(notes);
    }

    public Note getNoteByPosition(int position) {
        return notes.get(position);
    }

    private void addNotes() {
        notes.add(new Note("Cats", "Beautiful cats", "01.01.27"));
        notes.add(new Note("Dogs", "Loyal dogs", "01.10.21"));
        notes.add(new Note("Gods", "Ancient gods", "21.11.19"));
        notes.add(new Note("Cities", "Europe's cities", "19.09.17"));
        notes.add(new Note("Countries", "Countries of South America", "28.02.11"));
    }
}
