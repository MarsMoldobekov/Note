package com.example.note.domain

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class FirestoreNotesRepository : NotesRepository {
    companion object {
        private const val NOTES = "notes"
        private const val TITLE = "title"
        private const val DESCRIPTION = "description"
        private const val DATE = "date"
    }

    private val firestore = FirebaseFirestore.getInstance()

    override fun getNotes(callback: Callback<List<Note>>) {
        firestore.collection(NOTES).get().addOnCompleteListener {
            if (it.isSuccessful) {
                val temp = ArrayList<Note>()
                val documentSnapshots: List<DocumentSnapshot> = it.result!!.documents

                for (documentSnapshot in documentSnapshots) {
                    val id = documentSnapshot.id
                    val title = documentSnapshot.getString(TITLE)
                    val description = documentSnapshot.getString(DESCRIPTION)
                    val date = documentSnapshot.getDate(DATE)

                    //TODO(check title, description, date for nullable)
                    temp.add(Note(id, title!!, description!!, date!!))
                }

                callback.onSuccess(temp)
            } else {
                it.exception?.let { exception -> callback.onError(exception) }
            }
        }
    }

    override fun add(title: String, description: String, callback: Callback<Note>) {
        val data = HashMap<String, Any>()
        val date = Date()

        data[TITLE] = title
        data[DESCRIPTION] = description
        data[DATE] = date

        firestore.collection(NOTES).add(data).addOnCompleteListener {
            if (it.isSuccessful) {
                callback.onSuccess(Note(it.result!!.id, title, description, date))
            } else {
                it.exception?.let { it1 -> callback.onError(it1) }
            }
        }
    }

    override fun delete(item: Note, callback: Callback<Any>) {
        firestore.collection(NOTES).document(item.id).delete().addOnCompleteListener {
            if (it.isSuccessful) {
                callback.onSuccess(Any())
            } else {
                it.exception?.let { it1 -> callback.onError(it1) }
            }
        }
    }
}