package com.example.arc_exapmle.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class NoteViewModel(application: Application, val userId: Int) :
    AndroidViewModel(application) {
    private var repository = NoteRepository(application, userId)
    private val allNotes: LiveData<List<NoteEntity>>

    init {
        allNotes = repository.getAllNotes()
    }

    fun insert(note: NoteEntity) {

        val noteUI = NoteUI(
            note.noteId,
            note.title,
            note.description,
            note.priority,
            note.userId
        )

        repository.insert(noteUI)
    }

    fun delete(note: NoteEntity) {

        val noteUI = NoteUI(
            note.noteId,
            note.title,
            note.description,
            note.priority,
            note.userId
        )

        repository.delete(noteUI)
    }

    fun update(note: NoteEntity) {

        val noteUI = NoteUI(
            note.noteId,
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

    fun <T : ViewModel?> create(modelClass: Class<T>?): T {
        return NoteViewModel(getApplication(), userId) as T
    }




}