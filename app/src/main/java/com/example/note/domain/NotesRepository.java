package com.example.note.domain;

import java.util.List;

public interface NotesRepository {
    void getNotes(Callback<List<Note>> callback);
    void add(String title, String description, Callback<Note> callback);
    void delete(Note item, Callback<Object> callback);
}
