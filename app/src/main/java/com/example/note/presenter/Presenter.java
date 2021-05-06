package com.example.note.presenter;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.note.domain.MainDomain;
import com.example.note.domain.Note;
import com.example.note.ui.Context;

import java.util.List;

public class Presenter implements Parcelable {
    private Context context;
    private final MainDomain domain;

    public Presenter(Context context) {
        this.context = context;
        this.domain = new MainDomain();
    }

    protected Presenter(Parcel in) {
        domain = in.readParcelable(MainDomain.class.getClassLoader());
    }

    public static final Creator<Presenter> CREATOR = new Creator<Presenter>() {
        @Override
        public Presenter createFromParcel(Parcel in) {
            return new Presenter(in);
        }

        @Override
        public Presenter[] newArray(int size) {
            return new Presenter[size];
        }
    };

    public List<Note> getNotes() {
        return domain.getNotes();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(domain, flags);
    }

    public Note onNotePressed(int position) {
        return domain.getNoteByPosition(position);
    }
}
