package com.example.note.domain;

import java.util.Objects;

public class Note {
    private static int count = 0;
    private final int id;
    private final String title;
    private final String description;
    private final String date;

    public Note(String title, String description, String date) {
        this.title = title;
        this.description = description;
        this.date = date;
        id = count++;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Note)) return false;
        Note note = (Note) o;
        return id == note.id &&
                Objects.equals(title, note.title) &&
                Objects.equals(description, note.description) &&
                Objects.equals(date, note.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, date);
    }
}
