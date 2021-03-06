package com.example.arc_exapmle.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arc_exapmle.note.NoteEntity
import com.example.arc_exapmle.note.NoteRepository
import com.example.arc_exapmle.note.NoteUI
import kotlinx.coroutines.launch

class AddNoteKtActivityViewModel(private val noteRepository: NoteRepository) : ViewModel() {

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

                viewModelScope.launch {

                    noteRepository.insert(
                        NoteUI(
                            noteEntity.noteId,
                            noteEntity.title,
                            noteEntity.description,
                            noteEntity.priority,
                            noteEntity.userId
                        )
                    )

                    toastMutableLiveData.value = "Note Added"


                }



                onSuccessMutableLiveData.value = true
            }
        }


    }


}