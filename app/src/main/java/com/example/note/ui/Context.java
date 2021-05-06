package com.example.note.ui;

import com.example.note.presenter.Presenter;

public interface Context {
    Presenter getPresenter();
    void onNotePressed(int position);
}
