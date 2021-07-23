package com.example.note.domain

interface NotesRepository {
    fun getNotes(callback: Callback<List<Note>>)
    fun add(title: String, description: String, callback: Callback<Note>)
    fun delete(item: Note, callback: Callback<Any>)
}