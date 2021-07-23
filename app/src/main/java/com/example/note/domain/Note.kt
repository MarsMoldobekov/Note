package com.example.note.domain

import java.util.Date

data class Note(
    val id: String,
    val title: String,
    val description: String,
    val date: Date
)