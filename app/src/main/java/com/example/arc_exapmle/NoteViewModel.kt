package com.example.arc_exapmle

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class NoteViewModel(application: Application) :
    AndroidViewModel(application) {
    private var repository= NoteRepository(application)
    private val allNotes: LiveData<List<Note>?>

    init {
        allNotes = repository.getAllNotes()
    }

    fun insert(note: Note) {
        repository.insert(note)
    }

    fun delete(note: Note) {
        repository.delete(note)
    }

    fun update(note: Note) {
        repository.update(note)
    }

    fun deleteAllNotes(/*note: Note*/) {
        repository.deleteAllNotes()
    }

    fun getAllNotes(): LiveData<List<Note>?> {
        return repository.getAllNotes()
    }


}