package com.example.note.domain;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class FirestoreNotesRepository implements NotesRepository {
    private final static String NOTES = "notes";
    private final static String TITLE = "title";
    private final static String DESCRIPTION = "description";
    private final static String DATE = "date";

    private final FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    public void getNotes(Callback<List<Note>> callback) {
        firestore.collection(NOTES).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                final List<Note> temp = new ArrayList<>();
                List<DocumentSnapshot> documentSnapshots = task.getResult().getDocuments();

                for (DocumentSnapshot documentSnapshot : documentSnapshots) {
                    String id = documentSnapshot.getId();
                    String title = documentSnapshot.getString(TITLE);
                    String description = documentSnapshot.getString(DESCRIPTION);
                    Date date = documentSnapshot.getDate(DATE);

                    temp.add(new Note(id, title, description, date));
                }

                callback.onSuccess(temp);
            } else {
                callback.onError(task.getException());
            }
        });
    }

    @Override
    public void add(String title, String description, Callback<Note> callback) {
        HashMap<String, Object> data = new HashMap<>();
        Date date = new Date();

        data.put(TITLE, title);
        data.put(DESCRIPTION, description);
        data.put(DATE, date);

        firestore.collection(NOTES).add(data).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                callback.onSuccess(new Note(task.getResult().getId(), title, description, date));
            } else {
                callback.onError(task.getException());
            }
        });
    }

    @Override
    public void delete(Note item, Callback<Object> callback) {
        firestore.collection(NOTES).document(item.getId()).delete().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                callback.onSuccess(new Object());
            } else {
                callback.onError(task.getException());
            }
        });
    }
}
