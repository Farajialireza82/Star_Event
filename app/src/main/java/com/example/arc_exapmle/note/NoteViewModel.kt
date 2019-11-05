package com.example.arc_exapmle.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class NoteViewModel(application: Application , val id: String) :
    AndroidViewModel(application) {
    private var repository= NoteRepository(application , id)
    private val allNotes: LiveData<List<NoteEntity>>

    init {
        allNotes = repository.getAllNotes()
    }

    fun insert(note: NoteEntity) {

        val noteUI = NoteUI(
            note.getNoteId(),
            note.title,
            note.description,
            note.priority,
            note.userId
        )

        repository.insert(noteUI)
    }

    fun delete(note: NoteEntity) {

        val noteUI = NoteUI(
            note.getNoteId(),
            note.title,
            note.description,
            note.priority,
            note.userId
        )

        repository.delete(noteUI)
    }

    fun update(note: NoteEntity) {

        val noteUI = NoteUI(
            note.getNoteId(),
            note.title,
            note.description,
            note.priority,
            note.userId
        )

        repository.update(noteUI)
    }

    fun deleteAllNotes() {
        repository.deleteAllNotes()
    }

    fun getAllNotes(): LiveData<List<NoteEntity>> {
        return repository.getAllNotes()
    }


}