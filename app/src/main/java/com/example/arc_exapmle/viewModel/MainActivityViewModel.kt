package com.example.arc_exapmle.viewModel

import androidx.lifecycle.*
import com.example.arc_exapmle.note.NoteEntity
import com.example.arc_exapmle.note.NoteRepository
import com.example.arc_exapmle.note.NoteUI
import com.example.arc_exapmle.user.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class MainActivityViewModel(private var repository: NoteRepository) : ViewModel() {


    val toastMutableLiveData: MutableLiveData<String> = MutableLiveData()

    val notesListSingleLiveEvent:SingleLiveEvent<List<UserEntity>> = SingleLiveEvent()

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


    fun getAllNotes(): Flow<List<NoteEntity>> {


        return repository.getAllNotes()

    }

   /* fun getAllNotesDistinctUntilChanged(): Flow<List<NoteEntity>> {

        return repository.getAllNotesDistinctUntilChanged()

    }*/


}