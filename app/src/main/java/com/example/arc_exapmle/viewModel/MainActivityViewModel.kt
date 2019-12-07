package com.example.arc_exapmle.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.arc_exapmle.note.NoteEntity
import com.example.arc_exapmle.note.NoteRepository
import com.example.arc_exapmle.note.NoteUI

class MainActivityViewModel(repository: NoteRepository) : ViewModel() {

    private var repository = repository
    val allNotes: LiveData<List<NoteEntity>> = repository.getAllNotes()

    val toastMutableLiveData: MutableLiveData<String> = MutableLiveData()

    fun deleteNote(noteUI: NoteUI) {

        repository.delete(noteUI)

        toastMutableLiveData.value = "Note deleted Successfully"

    }

    fun deleteAllNotes() {

        repository.deleteAllNotes()

        toastMutableLiveData.value = "All notes deleted"

    }


}