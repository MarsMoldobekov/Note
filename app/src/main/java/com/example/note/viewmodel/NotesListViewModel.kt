package com.example.note.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.note.domain.Callback
import com.example.note.domain.FirestoreNotesRepository
import com.example.note.domain.Note
import com.example.note.domain.NotesRepository

class NotesListViewModel : ViewModel() {
    private val notesLiveData = MutableLiveData<List<Note>>()
    private val selectedNote = MutableLiveData<Note>()
    private val isSelected = MutableLiveData(false)
    private val repository: NotesRepository = FirestoreNotesRepository()

    fun getNotesLiveData(): LiveData<List<Note>> = notesLiveData

    fun getSelectedNote(): LiveData<Note> = selectedNote

    //TODO(rename this method)
    fun getIsSelected(): LiveData<Boolean> = isSelected

    fun requestNotes() {
        repository.getNotes(object : Callback<List<Note>> {
            override fun onSuccess(value: List<Note>) {
                notesLiveData.value = value
            }

            override fun onError(error: Throwable) {
                //TODO(implement handling an error)
            }
        })
    }

    fun onNoteClicked(position: Int) {
        isSelected.value = true
        selectedNote.value = notesLiveData.value!![position]
    }

    //TODO(rename this method)
    fun noteClose() {
        isSelected.value = false
    }
}