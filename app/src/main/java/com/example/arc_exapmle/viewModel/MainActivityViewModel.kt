package com.example.arc_exapmle.viewModel

import androidx.lifecycle.*
import com.example.arc_exapmle.note.NoteEntity
import com.example.arc_exapmle.note.NoteRepository
import com.example.arc_exapmle.note.NoteUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainActivityViewModel(private var repository: NoteRepository) : ViewModel() {


    val toastMutableLiveData: MutableLiveData<String> = MutableLiveData()

    fun deleteNote(noteUI: NoteUI) {

        viewModelScope.launch {

            repository.delete(noteUI)

            toastMutableLiveData.value = "Note deleted Successfully"
        }


    }

    fun deleteAllNotes() {


        viewModelScope.launch {

            repository.deleteAllNotes()


            toastMutableLiveData.value = "All notes deleted"
        }


    }


    fun getAllNotes(): LiveData<List<NoteEntity>> {


        return repository.getAllNotes().asLiveData()

    }


}