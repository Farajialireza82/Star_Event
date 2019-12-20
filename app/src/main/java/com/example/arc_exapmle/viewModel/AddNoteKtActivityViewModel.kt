package com.example.arc_exapmle.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.arc_exapmle.note.NoteEntity
import com.example.arc_exapmle.note.NoteRepository
import com.example.arc_exapmle.note.NoteUI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.random.Random

class AddNoteKtActivityViewModel(noteRepository: NoteRepository) : ViewModel() {

    private val noteRepository = noteRepository

    val titleMutableLiveData: MutableLiveData<String> = MutableLiveData()
    val descriptionMutableLiveData: MutableLiveData<String> = MutableLiveData()
    val toastMutableLiveData: MutableLiveData<String> = MutableLiveData()

    val onSuccessMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()


    fun addNote(title: String, description: String, priority: Int) {


        when {
            title.trim().isEmpty() -> {

                titleMutableLiveData.value = "This field can not remain empty"

                return

            }
            description.trim().isEmpty() -> {

                descriptionMutableLiveData.value = "This field can not remain empty"

                return

            }
            else -> {
                val noteEntity = NoteEntity(title, description, priority, noteRepository.userId)

                CoroutineScope(Default).launch {

                    noteRepository.insert(
                        NoteUI(
                            noteEntity.noteId,
                            noteEntity.title,
                            noteEntity.description,
                            noteEntity.priority,
                            noteEntity.userId
                        )
                    )
                }




                toastMutableLiveData.value = "Note Added Successfully "

                onSuccessMutableLiveData.value = true
            }
        }


    }


}