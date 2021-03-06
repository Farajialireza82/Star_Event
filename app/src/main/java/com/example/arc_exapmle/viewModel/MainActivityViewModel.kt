package com.example.arc_exapmle.viewModel

import androidx.lifecycle.*
import com.example.arc_exapmle.note.NoteEntity
import com.example.arc_exapmle.note.NoteRepository
import com.example.arc_exapmle.note.NoteUI
import com.example.arc_exapmle.user.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class MainActivityViewModel(private var repository: NoteRepository) : ViewModel() {

    val toastMutableLiveData: MutableLiveData<String> = MutableLiveData()

    val noteUIListMutableLiveData: MutableLiveData<List<NoteUI>> = MutableLiveData()

    fun deleteNote(noteUI: NoteUI) {

        viewModelScope.launch {

            repository.delete(noteUI)

        }


    }

    fun deleteAllNotes() {


        viewModelScope.launch {

            repository.deleteAllNotes()



            toastMutableLiveData.value = "All notes deleted"
        }


    }

    fun getAllNotes() {

        viewModelScope.launch {


            repository.getAllNotes().distinctUntilChanged().collect() {

                val noteUIList: MutableList<NoteUI> = ArrayList()

                it.map { noteEntity ->

                    noteUIList.add(noteEntityToNoteUI(noteEntity))

                }
                noteUIListMutableLiveData.value = noteUIList

            }


        }
    }

    private fun noteEntityToNoteUI(oldNoteEntity: NoteEntity):NoteUI{
        return   NoteUI(
            oldNoteEntity.noteId,
            oldNoteEntity.title,
            oldNoteEntity.description,
            oldNoteEntity.priority,
            repository.userId
        )
    }


}
