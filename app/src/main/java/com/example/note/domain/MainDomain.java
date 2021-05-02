package com.example.note.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class MainDomain implements Parcelable {
    NotesRepository repository;

    public MainDomain() {
        repository = new NotesRepository();
    }

    protected MainDomain(Parcel in) {
    }

    public static final Creator<MainDomain> CREATOR = new Creator<MainDomain>() {
        @Override
        public MainDomain createFromParcel(Parcel in) {
            return new MainDomain(in);
        }

        @Override
        public MainDomain[] newArray(int size) {
            return new MainDomain[size];
        }
    };

    public List<Note> getNotes() {
        return repository.getNotes();
    }

    public Note getNoteByPosition(int position) {
        return repository.getNotes().get(position);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
