package com.example.note.domain.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NotesRepository {
    public List<Note> getNotes() {
        List<Note> notes = new ArrayList<>();

        notes.add(new Note("Cats", "Beautiful cats", "01.01.27"));
        notes.add(new Note("Dogs", "Loyal dogs", "01.10.21"));
        notes.add(new Note("Gods", "Ancient gods", "21.11.19"));
        notes.add(new Note("Cities", "Europe's cities", "19.09.17"));
        notes.add(new Note("Countries", "Countries of South America", "28.02.11"));

        return Collections.unmodifiableList(notes);
    }
}
