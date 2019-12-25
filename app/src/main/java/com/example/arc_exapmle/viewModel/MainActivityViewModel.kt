package com.example.arc_exapmle.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arc_exapmle.note.NoteEntity
import com.example.arc_exapmle.note.NoteRepository
import com.example.arc_exapmle.note.NoteUI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivityViewModel(private var repository: NoteRepository) : ViewModel() {

     private val allNotes = repository.getAllNotes()

    val toastMutableLiveData: MutableLiveData<String> = MutableLiveData()

    fun deleteNote(noteUI: NoteUI) {

        viewModelScope.launch {

            repository.delete(noteUI)

        }



        toastMutableLiveData.value = "Note deleted Successfully"

    }

    fun deleteAllNotes() {


        viewModelScope.launch {

            repository.deleteAllNotes()


        }


        toastMutableLiveData.value = "All notes deleted"

    }


    fun getAllNotes(): LiveData<List<NoteEntity>> {

        return allNotes

    }


}