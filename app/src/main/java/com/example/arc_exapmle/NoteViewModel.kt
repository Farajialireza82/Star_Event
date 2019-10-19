package com.example.arc_exapmle

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class NoteViewModel(application: Application) :
    AndroidViewModel(application) {
    private var repository= NoteRepository(application)
    private val allNotes: LiveData<List<NoteEntity>>

    init {
        allNotes = repository.getAllNotes()
    }

    fun insert(note: NoteEntity) {
        repository.insert(note)
    }

    fun delete(note: NoteEntity) {
        repository.delete(note)
    }

    fun update(note: NoteEntity) {
        repository.update(note)
    }

    fun deleteAllNotes() {
        repository.deleteAllNotes()
    }

    fun getAllNotes(): LiveData<List<NoteEntity>> {
        return repository.getAllNotes()
    }


}