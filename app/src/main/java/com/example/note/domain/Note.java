package com.example.note.domain;

import java.util.Date;
import java.util.Objects;

public class Note {
    private final String id;
    private final String title;
    private final String description;
    private final Date date;

    public Note(String id, String title, String description, Date date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Note)) return false;
        Note note = (Note) o;
        return id.equals(note.id) &&
                Objects.equals(title, note.title) &&
                Objects.equals(description, note.description) &&
                Objects.equals(date, note.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, date);
    }
}
