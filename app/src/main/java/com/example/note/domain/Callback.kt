package com.example.note.domain

interface Callback<T> {
    fun onSuccess(value: T)
    fun onError(error: Throwable)
}