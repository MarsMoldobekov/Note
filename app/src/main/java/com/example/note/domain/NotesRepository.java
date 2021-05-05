package com.example.note.domain;

import java.util.List;

public interface NotesRepository {
    List<Note> getNotes();
}
