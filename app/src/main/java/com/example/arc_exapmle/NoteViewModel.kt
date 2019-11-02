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

        val noteUI = NoteUI(note.getNoteId() , note.title , note.description , note.priority)

        repository.insert(noteUI)
    }

    fun delete(note: NoteEntity) {

        val noteUI = NoteUI(note.getNoteId() , note.title , note.description , note.priority)

        repository.delete(noteUI)
    }

    fun update(note: NoteEntity) {

        val noteUI = NoteUI(note.getNoteId() , note.title , note.description , note.priority)

        repository.update(noteUI)
    }

    fun deleteAllNotes() {
        repository.deleteAllNotes()
    }

    fun getAllNotes(): LiveData<List<NoteEntity>> {
        return repository.getAllNotes()
    }


}